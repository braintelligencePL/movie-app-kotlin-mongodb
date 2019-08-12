package pl.braintelligence.requirement.task.infrastructure.external.mongo.catalog.entities

import org.springframework.data.mongodb.core.mapping.Document
import pl.braintelligence.requirement.task.infrastructure.external.mongo.Collections
import java.time.LocalDate
import java.time.LocalTime

@Document(collection = Collections.CATALOGS_COLLECTION)
class DbCatalog(
        val name: String,
        val movies: List<DbCinemaMovie> = emptyList()
)

class DbCinemaMovie(
        val title: String?,
        val imdbId: String,
        val showTime: DbShowTime,
        val price: String
)

class DbShowTime(
        val time: LocalTime,
        val date: LocalDate
)
