package pl.braintelligence.requirement.task.domain.core.catalog

import arrow.core.Try
import arrow.core.getOrElse
import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.api.catalog.dto.CatalogToUpdate
import pl.braintelligence.requirement.task.api.catalog.dto.NewCatalog
import pl.braintelligence.requirement.task.domain.Price
import pl.braintelligence.requirement.task.domain.exceptions.InvalidPriceException
import pl.braintelligence.requirement.task.infrastructure.external.client.MovieClient
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCatalog
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbCinemaMovie
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbShowTime
import java.math.BigDecimal
import java.util.*

@Service
class CatalogService(
        private val catalogRepository: CatalogRepository,
        private val movieClient: MovieClient
) {
    fun catalogService(newCatalog: NewCatalog) = catalogRepository.save(newCatalog.name)

    fun getAllCatalogs(): List<Catalog> = catalogRepository.findAll()

    fun updateCatalog(catalogToUpdate: CatalogToUpdate) = catalogRepository.run {
        updateCatalog(prepareDbCatalog(catalogToUpdate))
    }

    private fun fetchTitleByImdbId(id: String): String? = movieClient.getTitleByImdbId(id)?.title

    private fun prepareDbCatalog(catalogToUpdate: CatalogToUpdate): DbCatalog {
        return catalogToUpdate.run {
            DbCatalog(
                    name = catalogName,
                    movies = movies.map {
                        DbCinemaMovie(
                                imdbId = it.imdbId,
                                title = fetchTitleByImdbId(it.imdbId),
                                price = Try {
                                    Price(
                                            BigDecimal(it.price.value),
                                            Currency.getInstance(it.price.currency)
                                    )
                                }.getOrElse { throw InvalidPriceException("Price must be number and have existing currency code according to ISO 4217!") },
                                showTime = DbShowTime(
                                        time = it.showTime.time,
                                        date = it.showTime.date
                                )
                        )
                    })
        }
    }


}
