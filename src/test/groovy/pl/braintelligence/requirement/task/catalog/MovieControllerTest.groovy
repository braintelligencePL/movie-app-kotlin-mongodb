package pl.braintelligence.requirement.task.catalog

import org.springframework.core.ParameterizedTypeReference
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.MovieCatalog
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.api.catalog.dto.ShowTimeDto
import pl.braintelligence.requirement.task.base.BaseTest
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog

import java.time.LocalDate
import java.time.LocalTime

import static java.util.Arrays.asList
import static pl.braintelligence.requirement.task.movie.stubs.MovieClientStubs.stubMovieApiResponseSearchById

class MovieControllerTest extends BaseTest {

    def "Should create new catalog and browse it"() {
        given:
        def newCatalog = new NewCatalog("catalog name")

        and: "create new catalog"
        post("/catalogs", newCatalog)

        when:
        def response = get("/catalogs", new ParameterizedTypeReference<List<DbCatalog>>() {})

        then:
        response.body[0].name == newCatalog.name
    }

    def "Should update existing catalog and browse it"() {
        given: "prepare new catalog"
        def catalogName = "catalog name"
        post("/catalogs", new NewCatalog(catalogName))

        and:
        def catalogToUpdate = prepareCatalogToUpdate(catalogName)

        and:
        stubMovieApiResponseSearchById()

        when: "catalog is updated"
        put("/catalogs", catalogToUpdate)
        def response = get("/catalogs", new ParameterizedTypeReference<List<DbCatalog>>() {})

        then:
        response != null
    }

    CatalogToUpdate prepareCatalogToUpdate(String catalogName) {
        new CatalogToUpdate(
                catalogName,
                asList(
                        new MovieCatalog(
                                "imdbId",
                                new ShowTimeDto(LocalTime.now(), LocalDate.now())
                                , "price"
                        )
                )
        )
    }


}
