package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(

        @NotBlank
        @Size(min = 8)
        String currentPassword,

        @NotBlank
        @Size(min = 8)
        String newPassword

) {
}
