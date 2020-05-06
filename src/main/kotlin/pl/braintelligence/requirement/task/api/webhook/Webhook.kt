package pl.braintelligence.requirement.task.api.webhook

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import pl.braintelligence.requirement.task.api.movie.ApiReviewController
import pl.braintelligence.requirement.task.api.review.dto.NewReviewDto
import pl.braintelligence.requirement.task.domain.core.review.ReviewService

@RestController
@RequestMapping("/api/webhook")
internal class WebhookController(
        private val reviewService: ReviewService
)  {

    @PostMapping("/paypal")
    fun paypal(
            @RequestBody event: String
    ) {
        println(event)
    }
}
