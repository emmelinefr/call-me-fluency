package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class InvalidPracticeDayDurationException extends RuntimeException {

    public InvalidPracticeDayDurationException() {
        super("Practice duration must be between 3 and 15 minutes.");
    }

}
