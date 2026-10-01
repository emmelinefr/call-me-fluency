package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class PracticeSessionNotFoundException extends RuntimeException {

    public PracticeSessionNotFoundException() {
        super("Practice session not found.");
    }

}
