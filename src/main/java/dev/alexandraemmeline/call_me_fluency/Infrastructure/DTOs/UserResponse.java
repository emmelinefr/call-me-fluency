package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.Level;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.Status;

import java.time.LocalDateTime;

public record UserResponse(

        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        Level level,
        Status status

) {
}
