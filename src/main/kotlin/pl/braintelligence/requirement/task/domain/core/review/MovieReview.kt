package pl.braintelligence.requirement.task.domain.core.review

import pl.braintelligence.requirement.task.domain.exceptions.InvalidRatingException

data class MovieReview(
        val movieId: String,
        val rating: Int,
        val review: String
) {
    init {
        validateRating(rating)
    }

    private fun validateRating(rating: Int) {
        if (rating < 1 || rating > 5) {
            throw InvalidRatingException("Rating must be between 1..5")
        }
    }
}
