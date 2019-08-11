package pl.braintelligence.requirement.task.domain.movie

import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.api.movie.dto.InternalReview
import pl.braintelligence.requirement.task.api.movie.dto.MovieDto
import pl.braintelligence.requirement.task.domain.review.ReviewRepository
import pl.braintelligence.requirement.task.infrastructure.external.mongo.movie.MovieClient

@Service
class MovieService(
        private val movieClient: MovieClient,
        private val reviewRepository: ReviewRepository
) {

    fun fetchMovieByTitle(title: String): MovieDto? {
        val movieApiResponse = fetchMovieFromApi(title)
        val movieDto = movieApiResponse?.let { MovieDto.toMovieDto(it) }

        val movieId = movieDto?.id
        val dbReview = movieId?.let { reviewRepository.findByMovieId(it) }

        movieDto?.internalReviews = InternalReview.toInternalReview(dbReview) ?: arrayListOf()

        return movieDto
    }

    private fun queryForInternalRating(movieId: String?): InternalReview {
        TODO("not implemented")
    }

    private fun fetchMovieFromApi(title: String) = movieClient.getMovieByTitle(title)

}
