package pl.braintelligence.requirement.task.infrastructure.external.mongo.review

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto
import pl.braintelligence.requirement.task.domain.review.ReviewRepository
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbMovieReview
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbReview


@Repository
interface MongoTeamCreationRepository : MongoRepository<DbMovieReview, String> {
    fun findByMovieId(id: String): DbMovieReview?
    fun save(dbMovieReview: DbMovieReview?)
}

@Component
class MongoReviewRepository(
        private val mongo: MongoTeamCreationRepository,
        private val mongoTemplate: MongoTemplate
) : ReviewRepository {

    override fun save(newReviewDto: NewReviewDto) {
        val dbMovieReview: DbMovieReview? = mongo.findByMovieId(newReviewDto.movieId)
                ?: DbMovieReview(movieId = newReviewDto.movieId)

        val newReview = newReviewDto.run { DbReview(rating, review) }

        dbMovieReview?.reviews?.run { add(newReview) }

        mongo.save(dbMovieReview)
    }

    override fun findByMovieId(id: String): DbMovieReview? = mongo.findByMovieId(id)

}
