package dev.alexandraemmeline.call_me_fluency.Infrastructure.Exceptions;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException() {
        super("Role not found.");
    }

}
