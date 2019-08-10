package pl.braintelligence.requirement.task.infrastructure.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.braintelligence.requirement.task.domain.exceptions.ClientException;
import pl.braintelligence.requirement.task.domain.exceptions.DomainException;
import pl.braintelligence.requirement.task.domain.exceptions.InvalidEntityException;
import pl.braintelligence.requirement.task.domain.exceptions.MissingEntityException;
import pl.braintelligence.requirement.task.domain.exceptions.InvalidAuthorizationException;
import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorMessage> handleClientException(ClientException ex, HttpServletRequest request) {
        logger.error(createLog(request, INTERNAL_SERVER_ERROR, ErrorCode.UNEXPECTED_ERROR, ex.getMessage()), ex);
        return handleException(ex, request, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidAuthorizationException.class)
    public ResponseEntity<ErrorMessage> handleClientException(InvalidAuthorizationException ex, HttpServletRequest request) {
        logger.error(createLog(request, UNAUTHORIZED, ErrorCode.UNEXPECTED_ERROR, ex.getMessage()), ex);
        return handleException(ex, request, UNAUTHORIZED);
    }

    @ExceptionHandler(MissingEntityException.class)
    public ResponseEntity<ErrorMessage> handleMissingEntity(MissingEntityException ex, HttpServletRequest request) {
        logger.error(createLog(request, NOT_FOUND, ErrorCode.UNEXPECTED_ERROR, ex.getMessage()), ex);
        return handleException(ex, request, NOT_FOUND);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorMessage> handleMissingEntity(InvalidEntityException ex, HttpServletRequest request) {
        logger.error(createLog(request, UNPROCESSABLE_ENTITY, ErrorCode.UNEXPECTED_ERROR, ex.getMessage()), ex);
        return handleException(ex, request, UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception ex, HttpServletRequest request) {
        logger.error(createLog(request, INTERNAL_SERVER_ERROR, ErrorCode.UNEXPECTED_ERROR, ex.getMessage()), ex);
        return status(INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(ErrorCode.UNEXPECTED_ERROR));
    }

    private ResponseEntity<ErrorMessage> handleException(DomainException ex, HttpServletRequest request, HttpStatus status) {
        return status(status)
                .body(new ErrorMessage(ex.getCode()));
    }

    private String createLog(HttpServletRequest request, HttpStatus status, ErrorCode code, String message) {
        return request.getMethod() + " " + request.getRequestURI() + " " + status.value() + " | " + code + " | " + message;
    }
}
