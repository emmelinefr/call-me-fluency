package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.UserLevel;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.UserStatus;

import java.time.LocalDateTime;

public record UserResponse(

        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        UserLevel userLevel,
        UserStatus userStatus

) {
}
