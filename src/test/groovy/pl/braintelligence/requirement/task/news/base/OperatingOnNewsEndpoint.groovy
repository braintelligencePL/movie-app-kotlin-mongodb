package pl.braintelligence.requirement.task.news.base

import org.springframework.http.ResponseEntity
import pl.braintelligence.requirement.task.application.dto.NewsDto
import pl.braintelligence.requirement.task.base.BaseHttpMethodsSpec

trait OperatingOnNewsEndpoint implements BaseHttpMethodsSpec {

    ResponseEntity queryForArticles(String query) {
        return get("/news?query=$query", NewsDto)
    }

    ResponseEntity getTopHeadlines() {
        return get("/news/pl/technology", NewsDto)
    }

    ResponseEntity getTopHeadlinesWithAnyResponse() {
        return get("/news/pl/technology", Object)
    }

    ResponseEntity getTopHeadlines(String category, String country) {
        return get("/news/$country/$category", Object)
    }
}