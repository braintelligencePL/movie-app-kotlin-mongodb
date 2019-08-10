package pl.braintelligence.requirement.task.domain.exceptions;

import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode;

public class ClientException extends DomainException {

    public ClientException(String message, ErrorCode code) {
        super(message, code);
    }

}
