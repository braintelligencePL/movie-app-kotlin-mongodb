package pl.braintelligence.requirement.task.domain.movie

import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.api.movie.dto.InternalReviews
import pl.braintelligence.requirement.task.api.movie.dto.MovieDto
import pl.braintelligence.requirement.task.infrastructure.external.movie.MovieClient

@Service
class MovieService(
        private val movieClient: MovieClient
) {

    fun fetchMovieByTitle(title: String): MovieDto? {
        val movieApiResponse = fetchMovieFromApi(title)

        return movieApiResponse?.let { MovieDto.toMovieDto(it) }
    }

    private fun queryForInternalRating(movieId: String?): InternalReviews {
        TODO("not implemented")
    }

    private fun fetchMovieFromApi(title: String) = movieClient.getMovieByTitle(title)

}
