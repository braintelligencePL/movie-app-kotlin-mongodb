package pl.braintelligence.requirement.task.api.review.dto

import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbInternalMovieReviews
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbMovieReview

data class NewReviewDto(
        val movieId: String,
        val rating: Int,
        val review: String
) {
    companion object {
        fun toDbReview(newReviewDto: NewReviewDto): DbInternalMovieReviews = newReviewDto.run {
            DbInternalMovieReviews(
                    movieId = movieId,
                    reviews = mutableListOf(DbMovieReview(rating = rating, review = review))
            )
        }
    }
}

