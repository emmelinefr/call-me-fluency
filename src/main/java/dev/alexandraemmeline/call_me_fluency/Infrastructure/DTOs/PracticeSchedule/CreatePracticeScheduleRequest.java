package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreatePracticeScheduleRequest(

        @NotNull
        Set<CreatePracticeDayRequest> practiceDays

) {
}
