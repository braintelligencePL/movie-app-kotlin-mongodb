package pl.braintelligence.requirement.task.domain.exceptions;

import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode;

public class InvalidAuthorizationException extends DomainException {

    public InvalidAuthorizationException(String message, ErrorCode code) {
        super(message, code);
    }

}
