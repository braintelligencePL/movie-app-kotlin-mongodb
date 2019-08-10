package pl.braintelligence.requirement.task.infrastructure.news;

import static java.util.Objects.*;
import static pl.braintelligence.requirement.task.application.utils.DtoMapper.*;
import static pl.braintelligence.requirement.task.application.utils.DtoMapper.mapToCountry;
import static pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode.API_IS_NOT_AVAILABLE;
import static pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode.RESOURCE_NOT_FOUND;
import static pl.braintelligence.requirement.task.domain.exceptions.utils.PreCondition.when;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.invoke.MethodHandles;
import java.net.URI;

import pl.braintelligence.requirement.task.domain.exceptions.ClientException;
import pl.braintelligence.requirement.task.domain.exceptions.MissingEntityException;
import pl.braintelligence.requirement.task.domain.news.News;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class NewsClient {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${news-service.top-headlines}")
    private String topHeadlinesUri;

    @Value("${news-service.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public NewsClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public News getTopHeadlines(String country, String category, String page) {

        URI targetUrl = UriComponentsBuilder.fromUriString(topHeadlinesUri)
                .queryParam("apiKey", apiKey)
                .queryParam("category", category)
                .queryParam("country", country)
                .queryParam("page", page)
                .build().toUri();

        logger.info("Getting top headlines with url={}", targetUrl);

        News news = new News(mapToCountry(country), mapToCategory(category));
        news.setArticles(fetchNews(targetUrl).getArticles());

        return news;
    }

    public News getArticlesByQuery(String query) {
        URI targetUrl = UriComponentsBuilder.fromUriString(topHeadlinesUri)
                .queryParam("apiKey", apiKey)
                .queryParam("q", query)
                .build().toUri();

        News news = new News();
        news.setArticles(fetchNews(targetUrl).getArticles());

        return news;
    }

    private News fetchNews(URI targetUrl) {
        try {
            News news = new News();

            logger.info("Fetching news with url={}", targetUrl);

            news.setArticles(
                    requireNonNull(restTemplate.getForEntity(targetUrl, News.class)
                            .getBody())
                            .getArticles());

            return news;
        } catch (RestClientException ex) {
            logger.error("News API doesn't respond.");
            throw new ClientException("News API doesn't respond.", API_IS_NOT_AVAILABLE);
        }
    }
}
