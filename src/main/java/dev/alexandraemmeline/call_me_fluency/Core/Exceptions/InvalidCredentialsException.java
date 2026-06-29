package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Invalid password.");
    }

}
