package pl.braintelligence.requirement.task.api.review

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.requirement.task.api.movie.ApiReviewController
import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto
import pl.braintelligence.requirement.task.domain.core.review.ReviewService

@RestController
@RequestMapping("/api/reviews")
internal class ReviewController(
        private val reviewService: ReviewService
) : ApiReviewController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun addRatingAndReviewForMovie(
            @RequestBody newReviewDto: NewReviewDto
    ) = reviewService.addRatingAndReviewForMovie(newReviewDto)
}
