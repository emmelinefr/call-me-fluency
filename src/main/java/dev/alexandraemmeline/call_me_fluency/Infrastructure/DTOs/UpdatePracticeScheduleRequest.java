package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdatePracticeScheduleRequest(

        @NotNull
        Set<UpdatePracticeDayRequest> practiceDays

) {
}
