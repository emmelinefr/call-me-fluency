package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class InvalidPracticeScheduleDurationException extends RuntimeException {

    public InvalidPracticeScheduleDurationException() {
        super("Practice duration must be between 3 and 15 minutes.");
    }

}
