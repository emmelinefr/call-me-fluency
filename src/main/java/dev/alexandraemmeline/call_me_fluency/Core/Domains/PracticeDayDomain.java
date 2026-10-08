package dev.alexandraemmeline.call_me_fluency.Core.Domains;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.DayOfWeek;

import java.time.LocalTime;
import java.util.Objects;

public class PracticeDayDomain {

    private final DayOfWeek dayOfWeek;
    private LocalTime time;


    //constructor and its validations
    public PracticeDayDomain(DayOfWeek dayOfWeek, LocalTime time, int duration) {
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "Day of week cannot be null.");
        this.time = Objects.requireNonNull(time, "Time cannot be null.");
    }


    //getters
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }


    //behaviors
    //changeTime
    public void changeTime(LocalTime time) {
        this.time = Objects.requireNonNull(time, "Time cannot be null.");
    }

}
