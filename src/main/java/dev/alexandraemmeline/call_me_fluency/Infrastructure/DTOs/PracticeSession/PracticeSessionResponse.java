package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.PracticeSessionStatus;

import java.time.LocalDateTime;

public record PracticeSessionResponse(

        Long id,
        LocalDateTime scheduledAt,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        Long durationInMinutes,
        PracticeSessionStatus status

) {
}
