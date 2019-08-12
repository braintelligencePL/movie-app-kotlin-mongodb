package pl.braintelligence.requirement.task.domain.core.review

import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbInternalMovieReviews

interface ReviewRepository {

    fun save(movieReview: MovieReview)

    fun findByMovieId(id: String): DbInternalMovieReviews?

}
