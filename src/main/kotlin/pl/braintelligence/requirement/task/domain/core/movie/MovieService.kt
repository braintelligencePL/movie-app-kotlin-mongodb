package pl.braintelligence.requirement.task.domain.core.movie

import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.domain.core.movie.values.InternalReview
import pl.braintelligence.requirement.task.domain.core.review.ReviewRepository
import pl.braintelligence.requirement.task.infrastructure.external.client.MovieClient

@Service
class MovieService(
        private val movieClient: MovieClient,
        private val reviewRepository: ReviewRepository
) {

    fun fetchMovieByTitle(title: String): Movie? {
        val movieApiResponse = fetchMovieFromApi(title)
        val movieDto = movieApiResponse?.let { Movie.toMovie(it) }

        val movieId = movieDto?.imDbId
        val dbReview = movieId?.let { reviewRepository.findByMovieId(it) }

        movieDto?.internalReviews = InternalReview.toInternalReview(dbReview) ?: arrayListOf()

        return movieDto
    }

    private fun fetchMovieFromApi(title: String) = movieClient.getMovieByTitle(title)

}
