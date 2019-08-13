package pl.braintelligence.requirement.task.catalog

import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.MovieCatalog
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.api.catalog.dto.ShowTimeDto
import pl.braintelligence.requirement.task.base.BaseTest
import pl.braintelligence.requirement.task.domain.core.catalog.Catalog
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog

import java.nio.charset.Charset
import java.time.LocalDate
import java.time.LocalTime

import static java.util.Arrays.asList
import static pl.braintelligence.requirement.task.movie.stubs.MovieClientStubs.stubMovieApiResponseSearchById

class MovieControllerTest extends BaseTest {

    @Autowired
    TestRestTemplate authRestTemplate

    @Value('${spring.security.user.name}')
    String authUsername

    @Value('${spring.security.user.password}')
    String authPassword

    private ShowTimeDto showTimeDto

    def setup() {
        showTimeDto = new ShowTimeDto(LocalTime.of(1, 1), LocalDate.of(2000, 1, 1))
    }

    def "Should create new catalog and browse it"() {
        given:
        def newCatalog = new NewCatalog("catalog name")

        and: "create new catalog"
        authRestTemplate.exchange("/internal/catalogs", HttpMethod.POST, payloadWithBasicAuthHeaders(newCatalog), Object)

        when:
        def response = get("/api/catalogs", new ParameterizedTypeReference<List<Catalog>>() {})

        then:
        response.body[0].name == newCatalog.name
    }

    def "Should update existing catalog and browse it"() {
        given: "prepare new catalog"
        def catalogName = "catalog name"
        authRestTemplate.exchange("/internal/catalogs", HttpMethod.POST, payloadWithBasicAuthHeaders(new NewCatalog(catalogName)), Object)

        and:
        def catalogToUpdate = prepareCatalogToUpdate(catalogName)

        and:
        stubMovieApiResponseSearchById()

        when: "catalog is updated"
        authRestTemplate.exchange("/internal/catalogs", HttpMethod.PUT, payloadWithBasicAuthHeaders(catalogToUpdate), Object)
        def response = get("/api/catalogs", new ParameterizedTypeReference<List<DbCatalog>>() {})

        then:
        with(response.body[0]) {
            name == catalogName
            with(movies[0]) {
                title == "The Fast and the Furious"
                imdbId == "imdbId"
                showTime.time == showTimeDto.time
                showTime.date == showTimeDto.date
                price == "price" // todo()
            }
        }
    }

    private HttpEntity<NewCatalog> payloadWithBasicAuthHeaders(Object object) {
        def entity = new HttpEntity<>(object, prepareBasicAuthHeaders(authUsername, authPassword))
        entity
    }

    private CatalogToUpdate prepareCatalogToUpdate(String catalogName) {
        new CatalogToUpdate(
                catalogName,
                asList(
                        new MovieCatalog(
                                "imdbId",
                                new ShowTimeDto(LocalTime.of(1, 1), LocalDate.of(2000, 1, 1)),
                                "price"
                        )
                )
        )
    }

    private HttpHeaders prepareBasicAuthHeaders(String username, String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")))
                String authHeader = "Basic " + new String(encodedAuth)
                set("Authorization", authHeader)
            }
        };
    }
}
