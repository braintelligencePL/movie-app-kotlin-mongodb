package pl.braintelligence.requirement.task.api.movie

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.requirement.task.domain.movie.MovieService

@RestController
@RequestMapping("/movies")
internal class MovieController(
        private val movieService: MovieService
) : MovieControllerApi {

    @GetMapping("/{title}/times")
    @ResponseStatus(HttpStatus.OK)
    override fun fetchMovieTimes(
            @PathVariable title: String
    ): String? = movieService.fetchMovieTimes(title)

}
