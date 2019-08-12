package pl.braintelligence.requirement.task.infrastructure.external.mongo.review

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import pl.braintelligence.requirement.task.domain.core.review.MovieReview
import pl.braintelligence.requirement.task.domain.core.review.ReviewRepository
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbInternalMovieReviews
import pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities.DbMovieReview
import pl.braintelligence.requirement.task.logger


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

    val log by logger()

    override fun save(movieReview: MovieReview) {
        val dbInternalMovieReviews: DbInternalMovieReviews? = fetchInternalReviewByMovieId(movieReview)

        val newReview = movieReview.run { DbMovieReview(rating, review) }

        dbInternalMovieReviews?.reviews?.run { add(newReview) }

        mongo.save(dbInternalMovieReviews)
    }

    private fun fetchInternalReviewByMovieId(movieReview: MovieReview) =
            (mongo.findByMovieId(movieReview.movieId)
                    ?: DbInternalMovieReviews(movieId = movieReview.movieId))

    override fun findByMovieId(id: String): DbInternalMovieReviews? =
            mongo.findByMovieId(id)

}
