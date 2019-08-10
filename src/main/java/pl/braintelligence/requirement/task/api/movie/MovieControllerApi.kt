package pl.braintelligence.requirement.task.api.movie

import io.swagger.annotations.ApiOperation

interface MovieControllerApi {

    @ApiOperation(value = "Fetch movie by title and return time of the movie")
    fun fetchMovieTimes(title: String): String?
}
