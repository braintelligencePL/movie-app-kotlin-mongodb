package pl.braintelligence.requirement.task.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.braintelligence.requirement.task.application.dto.NewsDto;
import pl.braintelligence.requirement.task.application.utils.DtoMapper;
import pl.braintelligence.requirement.task.infrastructure.news.NewsClient;

import java.lang.invoke.MethodHandles;

@Service
public class NewsService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final NewsClient newsClient;

    public NewsService(NewsClient newsClient) {
        this.newsClient = newsClient;
    }

    public NewsDto getTopHeadlines(String country, String category, String page) {
        logger.info("Getting top headlines from NewsAPI for category={}, country={}", category, country);
        return DtoMapper.mapToNewsDto(newsClient.getTopHeadlines(country, category, page));
    }

    public NewsDto getArticlesByQuery(String query) {
        logger.info("Getting news from NewsAPI for query={}", query);
        return DtoMapper.mapToQueryNewsDto(newsClient.getArticlesByQuery(query));
    }
}
