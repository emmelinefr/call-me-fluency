package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.User;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.UserLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8, message = "Password must contain at least 8 characters.")
        String password,
        
        @NotNull
        UserLevel userLevel

) {
}
