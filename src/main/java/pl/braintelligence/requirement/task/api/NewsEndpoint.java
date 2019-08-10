package pl.braintelligence.requirement.task.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.braintelligence.requirement.task.application.NewsService;
import pl.braintelligence.requirement.task.application.dto.NewsDto;

@RestController
@RequestMapping("/news")
public class NewsEndpoint {

    private NewsService newsService;

    public NewsEndpoint(NewsService newsService) {
        this.newsService = newsService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{country}/{category}")
    public NewsDto getTopHeadlines(
            @PathVariable String country,
            @PathVariable String category,
            @RequestParam(value = "page", defaultValue = "1") String page
    ) {
        return newsService.getTopHeadlines(country, category, page);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public NewsDto getArticlesByQuery(
            @RequestParam(value = "query") String query
    ) {
        return newsService.getArticlesByQuery(query);
    }
}
