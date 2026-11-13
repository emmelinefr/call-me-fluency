package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class PracticeSessionAlreadyExistsException extends RuntimeException {

    public PracticeSessionAlreadyExistsException() {
        super("Practice session already exists.");
    }

}
