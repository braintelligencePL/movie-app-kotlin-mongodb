package pl.braintelligence.requirement.task.movie

import pl.braintelligence.requirement.task.base.BaseTest

import static org.springframework.http.HttpStatus.UNAUTHORIZED
import static pl.braintelligence.requirement.task.movie.stubs.MovieClientStubs.stubInvalidApiKey

class MovieClientTest extends BaseTest {

    def "Should respond with API_IS_NOT_AVAILABLE when API key incorrect"() {
        given:
        stubInvalidApiKey()

        when:
        def response = get("/movies?title=fast", Object)

        then:
        response.statusCode == UNAUTHORIZED
        response.body.code == "API_IS_NOT_AVAILABLE"
    }
}
