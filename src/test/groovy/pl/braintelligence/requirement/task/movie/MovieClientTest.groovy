package pl.braintelligence.requirement.task.movie

import pl.braintelligence.requirement.task.base.BaseIntegrationSpec

import static pl.braintelligence.requirement.task.movie.MovieClientStubs.stubMovieApiResponse

class MovieClientTest extends BaseIntegrationSpec {

    def "Should create new team and browse it"() {
        given:
        stubMovieApiResponse()

        when:
        def response = get('/movies/fast/times', String)

        then:
        response.body != null
    }
}
