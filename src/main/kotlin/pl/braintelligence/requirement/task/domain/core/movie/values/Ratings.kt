package pl.braintelligence.requirement.task.domain.core.movie.values

import pl.braintelligence.requirement.task.infrastructure.external.client.dto.Rating

data class Ratings(
        val ratings: List<Rating>
) {
    companion object {
        fun toRatingsDto(ratings: List<Rating>): List<ExternalRating> = ratings.map {
            ExternalRating(
                    source = it.source,
                    value = it.value)
        }

    }
}
