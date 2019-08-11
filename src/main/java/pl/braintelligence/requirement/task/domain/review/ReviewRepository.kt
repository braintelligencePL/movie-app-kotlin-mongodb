package pl.braintelligence.requirement.task.domain.review

import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbInternalMovieReviews

interface ReviewRepository {

    fun save(dbReview: MovieReview)

    fun findByMovieId(id: String): DbInternalMovieReviews?

}
