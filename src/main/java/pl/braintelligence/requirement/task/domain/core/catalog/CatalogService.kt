package pl.braintelligence.requirement.task.domain.core.catalog

import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.infrastructure.external.client.MovieClient
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCinemaMovie
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbShowTime

@Service
class CatalogService(
        private val catalogRepository: CatalogRepository,
        private val movieClient: MovieClient
) {
    fun catalogService(newCatalog: NewCatalog) = catalogRepository.save(newCatalog.name)

    fun getAllCatalogs(): List<DbCatalog> = catalogRepository.findAll()

    fun updateCatalog(catalogToUpdate: CatalogToUpdate) {

        val dbCatalog = catalogToUpdate.run {
            DbCatalog(
                    name = catalogName,
                    movies = movies.map {
                        DbCinemaMovie(
                                imdbId = it.imdbId,
                                title = fetchTitleByImdbId(it.imdbId),
                                price = it.price,
                                showTime = DbShowTime(
                                        time = it.showTime.time,
                                        date = it.showTime.date
                                )
                        )
                    })
        }

        catalogRepository.updateCatalog(dbCatalog)
    }

    private fun fetchTitleByImdbId(id: String): String? = movieClient.getTitleByImdbId(id)?.title

}
