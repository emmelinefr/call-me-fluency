package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.DayOfWeek;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CreatePracticeDayRequest(

        @NotNull
        DayOfWeek dayOfWeek,

        @NotNull
        LocalTime time

) {
}
