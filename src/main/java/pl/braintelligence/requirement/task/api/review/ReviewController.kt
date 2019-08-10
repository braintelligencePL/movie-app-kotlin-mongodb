package pl.braintelligence.requirement.task.api.movie

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto
import pl.braintelligence.requirement.task.domain.rating.RatingService

@RestController
@RequestMapping("/reviews")
internal class RatingController(
        private val ratingService: RatingService
) : ApiReviewController {

    @PostMapping
    override fun addRatingAndReviewForMovie(
            @RequestBody newReviewDto: NewReviewDto
    ) {
        ratingService.addRatingAndReviewForMovie()
    }

}
