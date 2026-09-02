package dev.alexandraemmeline.call_me_fluency.Core.Domains;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.DayOfWeek;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidPracticeScheduleDurationException;

import java.time.LocalTime;
import java.util.Objects;

public class PraticeScehduleDomain {

    private Long id;
    private UserDomain userDomain;
    private DayOfWeek dayOfWeek;
    private LocalTime time;
    private int duration;
    private boolean active;


    public PraticeScehduleDomain(UserDomain userDomain, DayOfWeek dayOfWeek, LocalTime time, int duration) {

        this.userDomain = Objects.requireNonNull(userDomain, "User cannot be null");
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "Day of week cannot be null");
        this.time = Objects.requireNonNull(time, "Time cannot be null");
        this.duration = validateDuration(duration);
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public UserDomain getUserDomain() {
        return userDomain;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isActive() {
        return active;
    }

    //TODO setters


    private int validateDuration(int duration) {
        if (duration < 3 || duration > 15) {
            throw new InvalidPracticeScheduleDurationException();
        }

        return duration;
    }


    public void activate() {
        this.active = true;
    }

    public void desactivate() {
        this.active = false;
    }

}
