package dev.alexandraemmeline.call_me_fluency.Core.Domains;

import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidPracticeDayDurationException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class PracticeDayDomain {

    private final DayOfWeek dayOfWeek;
    private LocalTime time;
    private int duration;


    //constructor and its validations
    public PracticeDayDomain(DayOfWeek dayOfWeek, LocalTime time, int duration) {
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "Day of week cannot be null.");
        this.time = Objects.requireNonNull(time, "Time cannot be null.");
        this.duration = validateDuration(duration);
    }

    private int validateDuration(int duration) {
        if (duration < 3 || duration > 15) {
            throw new InvalidPracticeDayDurationException();
        }

        return duration;
    }


    //getters
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }


    //behaviors
    //changeTime
    public void changeTime(LocalTime time) {
        this.time = Objects.requireNonNull(time, "Time cannot be null.");
    }

    //changeDuration
    public void changeDuration(int duration) {
        this.duration = validateDuration(duration);
    }
}
