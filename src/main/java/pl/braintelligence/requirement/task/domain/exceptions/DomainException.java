package pl.braintelligence.requirement.task.domain.exceptions;

import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode;

public abstract class DomainException extends RuntimeException {

    private ErrorCode code;

    DomainException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
