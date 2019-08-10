package pl.braintelligence.requirement.task.domain.rating

import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.infrastructure.external.movie.MovieClient

@Service
class RatingService(
        private val movieClient: MovieClient
) {
    fun addRatingAndReviewForMovie() {
        TODO("not implemented")
    }

}
