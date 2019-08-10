package pl.braintelligence.requirement.task.domain.exceptions;

import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode;

public class InvalidEntityException extends DomainException {

    public InvalidEntityException(String message, ErrorCode code) {
        super(message, code);
    }
}
