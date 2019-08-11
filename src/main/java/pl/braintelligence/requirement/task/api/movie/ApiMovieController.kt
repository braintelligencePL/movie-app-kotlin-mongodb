package pl.braintelligence.requirement.task.api.movie

import io.swagger.annotations.ApiOperation
import pl.braintelligence.requirement.task.api.movie.dto.MovieDto

interface ApiMovieController {

    @ApiOperation(value = "Fetch movie by title")
    fun fetchMovieByTitle(title: String): MovieDto?

}
