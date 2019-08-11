package pl.braintelligence.requirement.task.api.movie.dto

import pl.braintelligence.requirement.task.infrastructure.external.mongo.movie.dto.MovieApiResponse
import pl.braintelligence.requirement.task.infrastructure.external.mongo.movie.dto.Rating
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbMovieReview

data class MovieDto(
        val id: String,
        val name: String,
        val movieTime: String,
        val description: String,
        val releaseDate: String,
        val externalRatings: MutableList<RatingDto>,
        var internalReviews: MutableList<InternalReview>
) {
    companion object {
        fun toMovieDto(movieApiResponse: MovieApiResponse): MovieDto = movieApiResponse.run {
            MovieDto(
                    id = imdbID,
                    name = title,
                    movieTime = runtime,
                    description = plot,
                    releaseDate = released,
                    externalRatings = mergeRatings(ratings, imdbRating),
                    internalReviews = arrayListOf()
            )
        }

        private fun mergeRatings(ratings: List<Rating>, imdbRating: String): MutableList<RatingDto> =
                run {
                    RatingDto(source = "Imdb", value = imdbRating)
                }.let {
                    RatingsDto.toRatingsDto(ratings)
                            .toMutableList()
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

data class InternalReview(
        val rating: String,
        val review: String
) {
    companion object {
        fun toInternalReview(dbMovieReview: DbMovieReview?): MutableList<InternalReview>? =
                dbMovieReview?.reviews?.map {
                    InternalReview(
                            it.rating.toString(),
                            it.review
                    )
                }?.toMutableList()
    }
}
