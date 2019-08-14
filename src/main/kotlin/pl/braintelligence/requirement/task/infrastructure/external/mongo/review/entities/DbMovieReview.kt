package pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pl.braintelligence.requirement.task.infrastructure.external.mongo.Collections
import java.time.Instant

@Document(collection = Collections.REVIEWS_COLLECTION)
class DbInternalMovieReviews(
        @Id val movieId: String,
        val reviews: MutableList<DbMovieReview> = arrayListOf()
)

data class DbMovieReview(
        val rating: Int,
        val review: String,
        val date: Instant = Instant.now()
)
