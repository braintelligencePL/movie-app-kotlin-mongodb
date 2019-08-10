package pl.braintelligence.requirement.task.api.review.dto

data class NewReviewDto(
        val movieId: String,
        val rating: Int,
        val review: String
)
