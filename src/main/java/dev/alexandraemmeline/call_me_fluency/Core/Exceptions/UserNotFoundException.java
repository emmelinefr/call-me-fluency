package dev.alexandraemmeline.call_me_fluency.Core.Exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found.");
    }

}
