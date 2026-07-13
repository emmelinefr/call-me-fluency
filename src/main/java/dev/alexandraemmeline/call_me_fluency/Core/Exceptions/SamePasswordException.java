package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class SamePasswordException extends RuntimeException {

    public SamePasswordException() {
        super("The new password must be different from the current password.");
    }

}
