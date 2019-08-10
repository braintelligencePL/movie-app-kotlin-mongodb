package pl.braintelligence.requirement.task.infrastructure.external.movie.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieDto(

        @JsonProperty("Title")
        val title: String,

        @JsonProperty("Runtime")
        val runtime: String


)
