package pl.braintelligence.requirement.task.movie

import org.springframework.http.HttpStatus
import pl.braintelligence.requirement.task.base.BaseTest

import static pl.braintelligence.requirement.task.movie.stubs.MovieClientStubs.stubInvalidApiKey

class MovieClientTest extends BaseTest {

    def "Should respond with API_IS_NOT_AVAILABLE when API key incorrect"() {
        given:
        stubInvalidApiKey()

        when:
        def response = get("/api/movies?title=fast", Object)

        then:
        response.statusCode == HttpStatus.UNAUTHORIZED
        response.body.code == "API_IS_NOT_AVAILABLE"
    }
}
