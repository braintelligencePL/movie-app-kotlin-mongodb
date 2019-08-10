package pl.braintelligence.requirement.task.news

import pl.braintelligence.requirement.task.base.BaseIntegrationSpec
import pl.braintelligence.requirement.task.news.base.OperatingOnNewsEndpoint

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import static pl.braintelligence.requirement.task.news.base.NewsEndpointStubs.stubNewsApiNotResponding

class NewsClientSpec extends BaseIntegrationSpec implements OperatingOnNewsEndpoint {

    def "Should return error when news-api isn't available"() {
        given: "prepare stub from news-api"
        stubNewsApiNotResponding()

        when: "user asks for top-headlines"
        def response = getTopHeadlinesWithAnyResponse()

        then: "news-api doesn't respond"
        response.statusCode == INTERNAL_SERVER_ERROR
        response.body.code == 'API_IS_NOT_AVAILABLE'
    }
}
