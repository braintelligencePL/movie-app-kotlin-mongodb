package pl.braintelligence.requirement.task.domain.exceptions.utils;

import pl.braintelligence.requirement.task.domain.exceptions.ClientException;
import pl.braintelligence.requirement.task.domain.exceptions.DomainException;
import pl.braintelligence.requirement.task.domain.exceptions.InvalidAuthorizationException;
import pl.braintelligence.requirement.task.domain.exceptions.InvalidEntityException;

import java.util.function.Supplier;

public class PreCondition {

    private boolean condition;

    private PreCondition(boolean condition) {
        this.condition = condition;
    }

    public static PreCondition when(boolean condition) {
        return new PreCondition(condition);
    }

    public void thenInvalidEntity(ErrorCode code, String message) {
        thenThrow(() -> new InvalidEntityException(message, code));
    }

    public void thenInvalidAuthorization(ErrorCode code, String message) {
        thenThrow(() -> new InvalidAuthorizationException(message, code));
    }

    public void thenClientError(ErrorCode code, String message) {
        thenThrow(() -> new ClientException(message, code));
    }

    private void thenThrow(Supplier<DomainException> exceptionCreator) {
        if (condition) {
            throw exceptionCreator.get();
        }
    }
}
