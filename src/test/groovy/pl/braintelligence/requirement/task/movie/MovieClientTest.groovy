package pl.braintelligence.requirement.task.movie


import pl.braintelligence.requirement.task.base.BaseSpec

import static org.springframework.http.HttpStatus.UNAUTHORIZED
import static pl.braintelligence.requirement.task.movie.MovieClientStubs.stubInvalidApiKey
import static pl.braintelligence.requirement.task.movie.MovieClientStubs.stubMovieApiResponse

class MovieClientTest extends BaseSpec {

    def "Should retrieve movie time"() {
        given: "prepare stub from movie-api"
        stubMovieApiResponse()

        when: "queering for movie by title"
        def response = get("/movies/someMovieTitle/times", String)

        then:
        response.body == "106 min"
    }

    def "Should respond with API_IS_NOT_AVAILABLE when API key incorrect"() {
        given:
        stubInvalidApiKey()

        when:
        def response = get("/movies/someMovieTitle/times", Object)

        then:
        response.statusCode == UNAUTHORIZED
        response.body.code == "API_IS_NOT_AVAILABLE"
    }
}
