package pl.braintelligence.requirement.task.movie.domain

import pl.braintelligence.requirement.task.domain.exceptions.InvalidRatingException
import pl.braintelligence.requirement.task.domain.review.MovieReview
import spock.lang.Specification

class ReviewCreationTest extends Specification {

    def "Should not create review when rating is not between 1..5"() {
        when:
        new MovieReview("movieId", 43, "review")

        then:
        def thrown = thrown(InvalidRatingException.class)
        thrown.message == "Rating must be between 1..5"
    }

}
