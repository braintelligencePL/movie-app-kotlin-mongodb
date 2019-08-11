package pl.braintelligence.requirement.task.domain.review

import org.springframework.stereotype.Service
import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto

@Service
class ReviewService(
        private val reviewRepository: ReviewRepository
) {
    fun addRatingAndReviewForMovie(newReviewDto: NewReviewDto) {
        reviewRepository.save(newReviewDto)
    }

}
