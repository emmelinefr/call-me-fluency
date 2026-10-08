package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.DayOfWeek;
import java.time.LocalTime;

public record PracticeDayResponse(

        DayOfWeek dayOfWeek,
        LocalTime time

) {
}
