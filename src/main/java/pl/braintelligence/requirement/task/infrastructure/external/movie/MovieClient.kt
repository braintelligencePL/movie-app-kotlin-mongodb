package pl.braintelligence.requirement.task.infrastructure.external.movie

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import pl.braintelligence.requirement.task.application.utils.DtoMapper.*
import pl.braintelligence.requirement.task.application.utils.DtoMapper.mapToCountry
import pl.braintelligence.requirement.task.domain.exceptions.ClientException
import pl.braintelligence.requirement.task.domain.exceptions.MissingEntityException
import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode.API_IS_NOT_AVAILABLE
import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode.RESOURCE_NOT_FOUND
import pl.braintelligence.requirement.task.domain.exceptions.utils.PreCondition.`when`
import pl.braintelligence.requirement.task.domain.news.News
import java.lang.invoke.MethodHandles
import java.util.*

package pl.braintelligence.requirement.task.infrastructure.news

import java.util.Objects.*
import pl.braintelligence.requirement.task.application.utils.DtoMapper.*
import pl.braintelligence.requirement.task.application.utils.DtoMapper.mapToCountry
import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode.API_IS_NOT_AVAILABLE
import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode.RESOURCE_NOT_FOUND
import pl.braintelligence.requirement.task.domain.exceptions.utils.PreCondition.`when`

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

import java.lang.invoke.MethodHandles
import java.net.URI


@Configuration
@EnableConfigurationProperties
class MovieClient(
        private val restTemplate: RestTemplate,
        @Value("\${omdb-api.uri-base}") private val omdbApiUrl: String,
        @Value("\${omdb-api.api-key}") private val omdbApiKey: String
) {
    fun getTopHeadlines(title: String): Objects {

        val targetUrl = UriComponentsBuilder.fromUriString(omdbApiUrl)
                .queryParam("apikey", omdbApiKey)
                .queryParam("t", title)
                .build().toUri()

        restTemplate.getForEntity(targetUrl, News::class.java)

        logger.info("Getting movies with url={}", targetUrl)

        news.setArticles(fetchNews(targetUrl).getArticles())

        return news
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    }
}
