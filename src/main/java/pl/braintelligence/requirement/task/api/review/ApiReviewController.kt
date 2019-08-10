package pl.braintelligence.requirement.task.api.movie

import io.swagger.annotations.ApiOperation
import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto

interface ApiReviewController {

    @ApiOperation(value = "Add rating and review for movie")
    fun addRatingAndReviewForMovie(newReviewDto: NewReviewDto)

}
