package pl.braintelligence.requirement.task.domain.core.movie

import pl.braintelligence.requirement.task.domain.core.movie.values.ExternalRating
import pl.braintelligence.requirement.task.domain.core.movie.values.InternalReview
import pl.braintelligence.requirement.task.domain.core.movie.values.Ratings
import pl.braintelligence.requirement.task.infrastructure.external.client.dto.MovieApiResponse
import pl.braintelligence.requirement.task.infrastructure.external.client.dto.Rating

data class Movie(
        val imDbId: String,
        val name: String,
        val movieTime: String,
        val description: String,
        val releaseDate: String,
        val externalRatings: MutableList<ExternalRating>,
        var internalReviews: MutableList<InternalReview>
) {
    companion object {
        fun toMovie(movieApiResponse: MovieApiResponse): Movie = movieApiResponse.run {
            Movie(
                    imDbId = imdbID,
                    name = title,
                    movieTime = runtime,
                    description = plot,
                    releaseDate = released,
                    externalRatings = mergeRatings(ratings, imdbRating),
                    internalReviews = arrayListOf()
            )
        }

        private fun mergeRatings(ratings: List<Rating>, imdbRating: String): MutableList<ExternalRating> =
                run {
                    ExternalRating(source = "Imdb", value = imdbRating)
                }.let {
                    Ratings.toRatingsDto(ratings)
                            .toMutableList()
                            .apply { add(it) }
                }
    }
}
