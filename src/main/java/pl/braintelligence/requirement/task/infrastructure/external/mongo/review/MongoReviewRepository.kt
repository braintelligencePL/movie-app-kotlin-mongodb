package pl.braintelligence.requirement.task.infrastructure.external.mongo.review

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto
import pl.braintelligence.requirement.task.domain.review.ReviewRepository
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbInternalMovieReviews
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbMovieReview


@Repository
interface MongoTeamCreationRepository : MongoRepository<DbInternalMovieReviews, String> {
    fun findByMovieId(id: String): DbInternalMovieReviews?
    fun save(dbInternalMovieReviews: DbInternalMovieReviews?)
}

@Component
class MongoReviewRepository(
        private val mongo: MongoTeamCreationRepository,
        private val mongoTemplate: MongoTemplate
) : ReviewRepository {

    override fun save(newReviewDto: NewReviewDto) {
        val dbInternalMovieReviews: DbInternalMovieReviews? = mongo.findByMovieId(newReviewDto.movieId)
                ?: DbInternalMovieReviews(movieId = newReviewDto.movieId)

        val newReview = newReviewDto.run { DbMovieReview(rating, review) }

        dbInternalMovieReviews?.reviews?.run { add(newReview) }

        mongo.save(dbInternalMovieReviews)
    }

    override fun findByMovieId(id: String): DbInternalMovieReviews? = mongo.findByMovieId(id)

}
