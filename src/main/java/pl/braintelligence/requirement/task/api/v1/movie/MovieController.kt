package pl.braintelligence.requirement.task.api.v1.movie

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.requirement.task.api.v1.movie.dto.MovieDto
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
    ): MovieDto? = movieService.fetchMovieByTitle(title)

}
