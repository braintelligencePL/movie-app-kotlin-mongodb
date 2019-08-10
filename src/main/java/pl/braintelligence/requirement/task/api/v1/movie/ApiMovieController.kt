package pl.braintelligence.requirement.task.api.v1.movie

import io.swagger.annotations.ApiOperation
import pl.braintelligence.requirement.task.api.v1.movie.dto.MovieDto
import pl.braintelligence.requirement.task.infrastructure.external.movie.dto.MovieApiResponse

interface ApiMovieController {

    @ApiOperation(value = "Fetch movie by title")
    fun fetchMovieByTitle(title: String): MovieDto?

}
