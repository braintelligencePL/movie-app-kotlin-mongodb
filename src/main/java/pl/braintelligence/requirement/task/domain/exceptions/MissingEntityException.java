package pl.braintelligence.requirement.task.domain.exceptions;

import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode;

public class MissingEntityException extends DomainException {

    public MissingEntityException(String message, ErrorCode code) {
        super(message, code);
    }

}
