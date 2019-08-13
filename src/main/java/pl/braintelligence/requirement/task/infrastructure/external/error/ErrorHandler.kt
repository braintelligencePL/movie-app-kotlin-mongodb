package pl.braintelligence.requirement.task.infrastructure.external.error


import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY
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
    fun handleClientApiException(ex: ApiResponseException, request: HttpServletRequest): ResponseEntity<ErrorMessage> {
        log.error(createLog(request, UNAUTHORIZED, ErrorCode.API_IS_NOT_AVAILABLE), ex)
        return status(UNAUTHORIZED)
                .body(ErrorMessage(ErrorCode.API_IS_NOT_AVAILABLE))
    }

    @ExceptionHandler(EntityAlreadyExist::class)
    fun handleMongoException(ex: EntityAlreadyExist, request: HttpServletRequest): ResponseEntity<ErrorMessage> {
        log.error(createLog(request, UNPROCESSABLE_ENTITY, ErrorCode.ENTITY_ALREADY_EXIST), ex)
        return status(UNPROCESSABLE_ENTITY)
                .body(ErrorMessage(ErrorCode.ENTITY_ALREADY_EXIST))
    }

    private fun createLog(
            request: HttpServletRequest,
            status: HttpStatus,
            code: ErrorCode)
            : String = "$request.method $request.requestURI $status.value $code"

}

internal class ErrorMessage(val code: ErrorCode)
