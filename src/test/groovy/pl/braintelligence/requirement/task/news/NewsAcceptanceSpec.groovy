package pl.braintelligence.requirement.task.news

import pl.braintelligence.requirement.task.base.BaseIntegrationSpec
import pl.braintelligence.requirement.task.news.base.OperatingOnNewsEndpoint

import static pl.braintelligence.requirement.task.news.base.NewsEndpointStubs.*

class NewsAcceptanceSpec extends BaseIntegrationSpec implements OperatingOnNewsEndpoint {

    def "Should return stubbed top-headlines articles from NewsAPI"() {
        given: "prepare stub for news"
        stubNewsApiTopHeadlinesResponse()

        when: "user asks for top-headlines"
        def response = getTopHeadlines()

        then: "verify that system response is correct"
        response.statusCode.is2xxSuccessful()
        response.body.articles.size == 20
        response.body.country == "pl"
        response.body.category == "technology"
        response.body.articles[12].date == "2018/10/10"
        response.body.articles[2].author == null
        response.body.articles[1].sourceName == "Test"
        response.body.articles[3].title == "Lepiej późno niż wcale."
        response.body.articles[1].articleUrl == "https://www.test.html"
        response.body.articles[3].description == "albo, albo"
        response.body.articles[2].imageUrl == "https://test.test"
    }

    def "Should query for stubbed articles from NewsAPI"() {
        given: "prepare stub for news"
        stubNewsApiQueryForArticlesResponse()

        when: "user query for 'red' articles"
        def response = queryForArticles("bitcoin")

        then: "verify that system response is correct"
        response.statusCode.is2xxSuccessful()
        response.body.articles.size == 1
        response.body.country == null
        response.body.category == null
        response.body.articles[0].date == "2018/10/10"
        response.body.articles[0].author == "Piotr Bicki"
        response.body.articles[0].sourceName == "test"
        response.body.articles[0].title == "Lepiej późno niż wcale..."
        response.body.articles[0].articleUrl == "https://www.test.html"
        response.body.articles[0].description == "Bitcoin is here."
        response.body.articles[0].imageUrl == "https://test.test"
    }

}
