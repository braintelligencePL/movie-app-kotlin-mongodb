package pl.braintelligence.requirement.task.infrastructure.external.client.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieApiResponse(

        @JsonProperty("Title")
        val title: String,

        @JsonProperty("Runtime")
        val runtime: String,

        @JsonProperty("Plot")
        val plot: String,

        @JsonProperty("Released")
        val released: String,

        @JsonProperty("Ratings")
        val ratings: List<Rating>,

        @JsonProperty("imdbRating")
        val imdbRating: String,

        @JsonProperty("imdbID")
        val imdbID: String
)

data class Rating(

        @JsonProperty("Source")
        val source: String,

        @JsonProperty("Value")
        val value: String
)
