package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreatePracticeDayRequest(

        @NotNull
        DayOfWeek dayOfWeek,

        @NotNull
        LocalTime time,

        @NotNull
        Integer duration

) {
}
