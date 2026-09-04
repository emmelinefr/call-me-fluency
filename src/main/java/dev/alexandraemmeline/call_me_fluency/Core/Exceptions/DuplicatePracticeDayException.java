package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class DuplicatePracticeDayException extends RuntimeException {

    public DuplicatePracticeDayException() {
        super("A practice is already scheduled for this day and time.");
    }

}
