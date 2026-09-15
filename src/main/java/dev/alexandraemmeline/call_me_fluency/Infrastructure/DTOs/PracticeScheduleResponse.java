package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs;

import java.util.Set;

public record PracticeScheduleResponse(

    Long id,
    boolean active,
    Set<PracticeDayResponse> practiceDays

) {
}
