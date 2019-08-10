package pl.braintelligence.requirement.task.infrastructure.external.movie

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import pl.braintelligence.requirement.task.infrastructure.external.error.ApiResponseException
import pl.braintelligence.requirement.task.infrastructure.external.error.ErrorCode
import pl.braintelligence.requirement.task.infrastructure.external.movie.dto.MovieDto
import pl.braintelligence.requirement.task.logger

@Configuration
@EnableConfigurationProperties
class MovieClient(
        private val restTemplate: RestTemplate,
        @Value("\${movie-api.uri-base}") private val movieApiURL: String,
        @Value("\${movie-api.api-key}") private val movieApiKey: String
) {

    private val log by logger()

    fun getMovieByTitle(title: String): MovieDto? = run {
        UriComponentsBuilder.fromUriString(movieApiURL)
                .queryParam("apikey", movieApiKey)
                .queryParam("t", title)
                .build()
                .toUri()
    }.also {
        log.info("Getting movies with url={}", it)
    }.let {
        try {
            restTemplate.getForEntity(it, MovieDto::class.java).body
        } catch (ex: RestClientException) {
            log.error("Movie API response message = ${ex.message}")
            throw ApiResponseException("Movie API response message = ${ex.message}", ErrorCode.API_IS_NOT_AVAILABLE)
        }
    }
}
