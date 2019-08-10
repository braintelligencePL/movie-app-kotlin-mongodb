package pl.braintelligence.requirement.task.infrastructure.error;


import pl.braintelligence.requirement.task.domain.exceptions.utils.ErrorCode;

class ErrorMessage {

    private ErrorCode code;

    ErrorMessage(ErrorCode code) {
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}