package pl.braintelligence.requirement.task.movie.domain

import pl.braintelligence.requirement.task.base.BaseTest
import pl.braintelligence.requirement.task.domain.core.review.MovieReview
import pl.braintelligence.requirement.task.domain.exceptions.InvalidRatingException

class ReviewCreationTest extends BaseTest {

    def "Should not create review when rating is not between 1..5"() {
        when:
        new MovieReview("movieId", 43, "review")

        then:
        def thrown = thrown(InvalidRatingException.class)
        thrown.message == "Rating must be between 1..5"
    }

}
