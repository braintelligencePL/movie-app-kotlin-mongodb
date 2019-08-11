package pl.braintelligence.requirement.task.api.movie

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.requirement.task.domain.movie.Movie
import pl.braintelligence.requirement.task.domain.movie.MovieService

@RestController
@RequestMapping("/movies")
internal class MovieController(
        private val movieService: MovieService
) : ApiMovieController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun fetchMovieByTitle(
            @RequestParam(required = true) title: String
    ): Movie? = movieService.fetchMovieByTitle(title)

}
