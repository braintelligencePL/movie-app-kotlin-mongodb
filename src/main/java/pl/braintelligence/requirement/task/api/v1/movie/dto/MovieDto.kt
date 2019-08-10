package pl.braintelligence.requirement.task.api.v1.movie.dto

import pl.braintelligence.requirement.task.domain.movie.InternalRating
import pl.braintelligence.requirement.task.infrastructure.external.movie.dto.MovieApiResponse
import pl.braintelligence.requirement.task.infrastructure.external.movie.dto.Rating

data class MovieDto(
        val name: String,
        val movieTime: String,
        val description: String,
        val releaseDate: String,
        val externalRatings: MutableList<RatingDto>,
        val internalRatings: List<InternalRating>
) {
    companion object {
        fun toMovieDto(movieApiResponse: MovieApiResponse): MovieDto = movieApiResponse.run {
            MovieDto(
                    name = title,
                    movieTime = runtime,
                    description = plot,
                    releaseDate = released,
                    externalRatings = mergeRatings(ratings, imdbRating),
                    internalRatings = emptyList()
            )
        }

        private fun mergeRatings(ratings: List<Rating>, imdbRating: String): MutableList<RatingDto> =
                run {
                    RatingDto(source = "Imdb", value = imdbRating)
                }.let {
                    RatingsDto.toRatingsDto(ratings).toMutableList()
                            .apply { add(it) }
                }
    }
}

data class RatingsDto(
        val ratings: List<Rating>
) {
    companion object {
        fun toRatingsDto(ratings: List<Rating>): List<RatingDto> = ratings.map {
            RatingDto(
                    source = it.source,
                    value = it.value)
        }

    }
}

data class RatingDto(
        val source: String,
        val value: String
)
