package pl.braintelligence.requirement.task.infrastructure.external.movie

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import pl.braintelligence.requirement.task.infrastructure.external.movie.dto.MovieDto
import java.lang.invoke.MethodHandles


@Configuration
@EnableConfigurationProperties
class MovieClient(
        private val restTemplate: RestTemplate,
        @Value("\${omdb-api.uri-base}") private val omdbApiUrl: String,
        @Value("\${omdb-api.api-key}") private val omdbApiKey: String
) {
    fun getMovieByTitle(title: String): MovieDto? {

        val targetUrl = UriComponentsBuilder.fromUriString(omdbApiUrl)
                .queryParam("apikey", omdbApiKey)
                .queryParam("t", title)
                .build()
                .toUri()

        logger.info("Getting movies with url={}", targetUrl)

        return restTemplate.getForEntity(targetUrl, MovieDto::class.java).body
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    }
}
