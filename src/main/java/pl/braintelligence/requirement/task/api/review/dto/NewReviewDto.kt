package pl.braintelligence.requirement.task.api.review.dto

import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbMovieReview
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbReview

data class NewReviewDto(
        val movieId: String,
        val rating: Int,
        val review: String
) {
    companion object {
        fun toDbReview(newReviewDto: NewReviewDto): DbMovieReview = newReviewDto.run {
            DbMovieReview(
                    movieId = movieId,
                    reviews = mutableListOf(DbReview(rating = rating, review = review))
            )
        }
    }
}

