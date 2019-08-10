package pl.braintelligence.requirement.task.news.domain

import pl.braintelligence.requirement.task.base.BaseIntegrationSpec
import pl.braintelligence.requirement.task.news.base.OperatingOnNewsEndpoint

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

class NewsCreationSpec extends BaseIntegrationSpec implements OperatingOnNewsEndpoint {

    def "Should return error when category doesn't exist"() {
        given:
        def nonExistentCategory = "tech 123"
        def country = "pl"

        when: "user asks for top-headlines articles from non-existent category"
        def response = getTopHeadlines(nonExistentCategory, country)

        then: "system response - category doesn't exist"
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.code == 'NONEXISTENT_CATEGORY'
    }

    def "Should return error when country doesn't exist"() {
        given:
        def category = "technology"
        def nonExistentCountry = "1234"

        when: "user asks for top-headlines articles from non-existent country"
        def response = getTopHeadlines(category, nonExistentCountry)

        then: "system response - country doesn't exist"
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.code == 'NONEXISTENT_COUNTRY'
    }

}
