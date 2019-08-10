package pl.braintelligence.requirement.task.application.utils;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import pl.braintelligence.requirement.task.application.dto.NewsArticle;
import pl.braintelligence.requirement.task.application.dto.NewsDto;
import pl.braintelligence.requirement.task.domain.news.News;
import pl.braintelligence.requirement.task.domain.values.Article;
import pl.braintelligence.requirement.task.domain.values.Category;
import pl.braintelligence.requirement.task.domain.values.Country;

public class DtoMapper {

    public static NewsDto mapToNewsDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setCountry(news.getCountry().toString().toLowerCase());
        newsDto.setCategory(news.getCategory().toString().toLowerCase());
        newsDto.setArticles(news.getArticles().stream()
                .map(DtoMapper::mapToNewsArticle)
                .collect(Collectors.toList()));
        return newsDto;
    }

    public static NewsDto mapToQueryNewsDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setCountry(null);
        newsDto.setCategory(null);
        newsDto.setArticles(news.getArticles().stream()
                .map(DtoMapper::mapToNewsArticle)
                .collect(Collectors.toList()));
        return newsDto;
    }

    private static NewsArticle mapToNewsArticle(Article article) {
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setAuthor(article.getAuthor());
        newsArticle.setArticleUrl(article.getUrl());
        newsArticle.setTitle(article.getTitle());
        newsArticle.setDescription(article.getDescription());
        newsArticle.setImageUrl(article.getUrlToImage());
        newsArticle.setSourceName(article.getSource().getName());
        newsArticle.setDate(
                new SimpleDateFormat("yyyy/MM/dd")
                        .format(article.getPublishedAt()));
        return newsArticle;
    }

    public static Country mapToCountry(String country) {
        if (isBlank(country)) {
            return Country.INVALID;
        }
        try {
            return Country.valueOf(country.toLowerCase());
        } catch (IllegalArgumentException e) {
            return Country.INVALID;
        }
    }

    public static Category mapToCategory(String category) {
        if (isBlank(category)) {
            return Category.INVALID;
        }
        try {
            return Category.valueOf(category.toLowerCase());
        } catch (IllegalArgumentException e) {
            return Category.INVALID;
        }
    }
}
