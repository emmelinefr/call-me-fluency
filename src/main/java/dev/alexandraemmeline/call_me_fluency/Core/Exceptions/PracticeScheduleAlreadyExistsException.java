package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class PracticeScheduleAlreadyExistsException extends RuntimeException {

    public PracticeScheduleAlreadyExistsException() {
        super("User already has a practice schedule.");
    }

}
