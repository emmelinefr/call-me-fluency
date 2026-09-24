package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class PracticeScheduleNotFoundException extends RuntimeException {

    public PracticeScheduleNotFoundException() {
        super("Practice schedule not found.");
    }

}
