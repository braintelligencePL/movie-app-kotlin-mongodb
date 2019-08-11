package pl.braintelligence.requirement.task.review


import pl.braintelligence.requirement.task.domain.movie.Movie
import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto
import pl.braintelligence.requirement.task.base.BaseSpec

import static pl.braintelligence.requirement.task.movie.stubs.MovieClientStubs.stubMovieApiResponse

class MovieControllerTest extends BaseSpec {

    def "Should should create new review and browse it"() {
        given: "prepare data"
        def sampleRating = 2
        def sampleReview = "good movie"
        def newReview = new NewReviewDto("tt0232500", sampleRating, sampleReview)

        and: "stubbed response from movie-api"
        stubMovieApiResponse()

        and: "new review created"
        post("/reviews", newReview)

        when: "get movie by title"
        def response = get("/movies?title=fast", Movie)

        then:
        with(response.body) {
            internalReviews.size() == 1
            with(internalReviews[0]) {
                rating == sampleRating.toString()
                review == sampleReview
            }
        }
    }
}
