package pl.braintelligence.requirement.task.domain.core.movie.values

import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbInternalMovieReviews

data class InternalReview(
        val rating: String,
        val review: String,
        val date: String
) {
    companion object {
        fun toInternalReview(dbInternalMovieReviews: DbInternalMovieReviews?): MutableList<InternalReview>? =
                dbInternalMovieReviews?.reviews?.map {
                    InternalReview(
                            it.rating.toString(),
                            it.review,
                            it.date.toString()
                    )
                }?.toMutableList()
    }
}
