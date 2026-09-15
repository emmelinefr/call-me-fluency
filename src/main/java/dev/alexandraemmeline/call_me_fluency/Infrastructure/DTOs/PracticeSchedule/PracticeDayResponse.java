package dev.alexandraemmeline.call_me_fluency.Infrastructure.DTOs.PracticeSchedule;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record PracticeDayResponse(

        DayOfWeek dayOfWeek,
        LocalTime time,
        Integer duration

) {
}
