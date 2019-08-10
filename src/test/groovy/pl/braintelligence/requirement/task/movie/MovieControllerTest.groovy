package pl.braintelligence.requirement.task.movie

import pl.braintelligence.requirement.task.api.movie.dto.MovieDto
import pl.braintelligence.requirement.task.base.BaseSpec

import static pl.braintelligence.requirement.task.movie.MovieClientStubs.stubMovieApiResponse

class MovieControllerTest extends BaseSpec {

    def "Should retrieve details of movie"() {
        given: "prepare stub from movie-api"
        stubMovieApiResponse()

        when: "queering for movie by title"
        def response = get("/movies?title=fast", MovieDto)

        then:
        with(response.body) {
            name == "The Fast and the Furious"
            movieTime == "106 min"
            releaseDate == "22 Jun 2001"
            with(externalRatings[0]) {
                source == "Internet Movie Database"
                value == "6.8/10"
            }
        }
    }
}
