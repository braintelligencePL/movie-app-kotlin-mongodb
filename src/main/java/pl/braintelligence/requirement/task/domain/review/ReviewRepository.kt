package pl.braintelligence.requirement.task.domain.review

import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbMovieReview

interface ReviewRepository {

    fun save(dbReview: NewReviewDto)

    fun findByMovieId(id: String): DbMovieReview?

}
