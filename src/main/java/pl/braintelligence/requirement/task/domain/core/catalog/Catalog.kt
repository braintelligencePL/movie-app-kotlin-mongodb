package pl.braintelligence.requirement.task.domain.core.catalog

import pl.braintelligence.requirement.task.domain.Price
import java.time.LocalDate
import java.time.LocalTime

class Catalog(
        val name: String,
        val movies: List<CinemaMovie> = emptyList()
)

class CinemaMovie(
        val title: String?,
        val imdbId: String,
        val showTime: ShowTime,
        val price: Price
)

class ShowTime(
        val time: LocalTime,
        val date: LocalDate
)
