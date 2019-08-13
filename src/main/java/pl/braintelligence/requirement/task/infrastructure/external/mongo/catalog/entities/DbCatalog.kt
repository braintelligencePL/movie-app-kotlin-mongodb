package pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities

import org.springframework.data.mongodb.core.mapping.Document
import pl.braintelligence.requirement.task.domain.core.catalog.Catalog
import pl.braintelligence.requirement.task.domain.core.catalog.CinemaMovie
import pl.braintelligence.requirement.task.domain.core.catalog.ShowTime
import pl.braintelligence.requirement.task.infrastructure.external.mongo.Collections
import pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities.DbShowTime.Companion.toShowTime
import java.time.LocalDate
import java.time.LocalTime

@Document(collection = Collections.CATALOGS_COLLECTION)
class DbCatalog @JvmOverloads constructor(
        val name: String,
        val movies: List<DbCinemaMovie> = emptyList()
) {
    companion object {
        fun toCatalogs(dbCatalogs: List<DbCatalog>): List<Catalog> = dbCatalogs.map { toCatalog(it) }

        private fun toCatalog(dbCatalog: DbCatalog): Catalog = dbCatalog.run {
            Catalog(
                    name = name,
                    movies = DbCinemaMovie.toMovies(movies)
            )
        }
    }
}

class DbCinemaMovie(
        val title: String?,
        val imdbId: String,
        val showTime: DbShowTime,
        val price: String
) {
    companion object {
        fun toMovies(movies: List<DbCinemaMovie>): List<CinemaMovie> = movies.map { toMovie(it) }

        private fun toMovie(dbCinemaMovie: DbCinemaMovie): CinemaMovie = dbCinemaMovie.run {
            CinemaMovie(
                    title = title,
                    imdbId = imdbId,
                    showTime = toShowTime(showTime),
                    price = price
            )
        }
    }
}

class DbShowTime(
        val time: LocalTime,
        val date: LocalDate
) {
    companion object {
        fun toShowTime(dbsShowTime: DbShowTime): ShowTime = dbsShowTime.run { ShowTime(time, date) }
    }
}
