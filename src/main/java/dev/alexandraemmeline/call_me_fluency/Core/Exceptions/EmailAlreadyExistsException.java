package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email already exists.");
    }

}
