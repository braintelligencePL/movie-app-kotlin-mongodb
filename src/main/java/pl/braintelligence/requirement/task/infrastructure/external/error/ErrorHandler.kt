package pl.braintelligence.requirement.task.infrastructure.external.error


import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import pl.braintelligence.requirement.task.logger
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
internal class ErrorHandler {

    private val log by logger()

    @ExceptionHandler(ApiResponseException::class)
    fun handleClientException(ex: ApiResponseException, request: HttpServletRequest): ResponseEntity<ErrorMessage> {
        log.error(createLog(request, UNAUTHORIZED, ErrorCode.API_IS_NOT_AVAILABLE), ex)
        return status(UNAUTHORIZED)
                .body(ErrorMessage(ErrorCode.API_IS_NOT_AVAILABLE))
    }

    private fun createLog(
            request: HttpServletRequest,
            status: HttpStatus,
            code: ErrorCode)
            : String = "$request.method $request.requestURI $status.value $code"

}

internal class ErrorMessage(val code: ErrorCode)
