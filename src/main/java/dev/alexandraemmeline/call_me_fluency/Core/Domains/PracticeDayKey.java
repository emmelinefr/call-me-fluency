package dev.alexandraemmeline.call_me_fluency.Core.Domains;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.DayOfWeek;

import java.time.LocalTime;

public record PracticeDayKey(

        DayOfWeek dayOfWeek,
        LocalTime time

) {
}
