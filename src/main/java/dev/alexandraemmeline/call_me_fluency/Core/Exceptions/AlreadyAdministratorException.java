package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class AlreadyAdministratorException extends RuntimeException {

    public AlreadyAdministratorException() {
        super("User is already an administrator.");
    }

}
