package pl.braintelligence.requirement.task.domain.movie

import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.infrastructure.external.movie.MovieClient
import pl.braintelligence.requirement.task.infrastructure.external.movie.dto.MovieDto

@Service
class MovieService(
        val movieClient: MovieClient
) {

    fun updateAndShowTimesPricesForMovies(): Any = TODO("not implemented")

    fun fetchMovieTimes(title: String): MovieDto? = movieClient.getMovieByTitle(title)


}
