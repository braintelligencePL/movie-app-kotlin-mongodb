package pl.braintelligence.requirement.task.domain.movie

import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.infrastructure.external.movie.MovieClient

@Service
class MovieService(
        val movieClient: MovieClient
) {

    fun fetchMovieTimes(title: String): String? =
            movieClient.getMovieByTitle(title)?.runtime ?: "Couldn't find a movie"

}
