package pl.braintelligence.requirement.task.api.movie

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.requirement.task.api.PublicEndpoint
import pl.braintelligence.requirement.task.api.movie.dto.UpdateTimesAndPrices
import pl.braintelligence.requirement.task.domain.movie.MovieService

@RestController
@RequestMapping("/movies")
internal class ProjectCreatorController(
        private val movieService: MovieService
) {

    @GetMapping("/{title}/times")
    @ResponseStatus(HttpStatus.OK)
    @PublicEndpoint
    fun fetchMovieTimes(
            @PathVariable title: String
    ): Any = movieService.fetchMovieTimes(title)

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun updateAndShowTimesPricesForMovies(
            @RequestBody updateTimesAndPrices: UpdateTimesAndPrices
    ): Any = movieService.updateAndShowTimesPricesForMovies()

}
