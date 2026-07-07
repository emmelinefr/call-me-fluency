package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(

        @NotBlank
        @Email
        String email,

        @NotBlank
        String currentPassword,

        @NotBlank
        @Size(min = 8)
        String newPassword

) {
}
