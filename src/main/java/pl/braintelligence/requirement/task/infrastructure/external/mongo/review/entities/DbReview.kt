package pl.braintelligence.requirement.task.infrastructure.external.mongo.review.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pl.braintelligence.requirement.task.infrastructure.external.mongo.Collections

@Document(collection = Collections.REVIEWS_COLLECTION)
class DbMovieReview(
        @Id val movieId: String,
        val reviews: MutableList<DbReview> = arrayListOf()
)

data class DbReview(
        val rating: Int,
        val review: String
)
