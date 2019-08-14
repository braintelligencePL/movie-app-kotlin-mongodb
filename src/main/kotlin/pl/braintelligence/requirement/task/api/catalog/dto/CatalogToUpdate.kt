package pl.braintelligence.requirement.task.api.catalog.dto

import java.time.LocalDate
import java.time.LocalTime


data class CatalogToUpdate(
        val catalogName: String,
        val movies: List<MovieCatalog>
)

class MovieCatalog(
        val imdbId: String,
        val showTime: ShowTimeDto,
        val price: PriceDto
)

data class PriceDto(
        val value: String,
        val currency: String
)

class ShowTimeDto(
        val time: LocalTime,
        val date: LocalDate
)
