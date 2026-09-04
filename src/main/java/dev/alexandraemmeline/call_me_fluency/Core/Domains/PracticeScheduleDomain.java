package dev.alexandraemmeline.call_me_fluency.Core.Domains;

import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.DuplicatePracticeDayException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PracticeScheduleDomain {

    private Long id;
    private UserDomain userDomain;
    private Set<PracticeDayDomain> practiceDays;
    private boolean active;



    //constructor
    public PracticeScheduleDomain(UserDomain userDomain) {
        this.userDomain = Objects.requireNonNull(userDomain, "User cannot be null.");
        this.practiceDays = new HashSet<>();
        this.active = true;
    }

    //getters
    public Long getId() {
        return id;
    }

    public UserDomain getUserDomain() {
        return userDomain;
    }

    public Set<PracticeDayDomain> getPracticeDays() {
        return Set.copyOf(practiceDays);
    }

    public boolean isActive() {
        return active;
    }

    //behaviors
    //addPracticeDay
    public void addPracticeDay(PracticeDayDomain practiceDay) {
        Objects.requireNonNull(practiceDay, "Practice day cannot be null.");

        boolean alreadyExists = practiceDays.stream()
                .anyMatch(existingPracticeDay ->
                        existingPracticeDay.getDayOfWeek() == practiceDay.getDayOfWeek()
                                && existingPracticeDay.getTime().equals(practiceDay.getTime()));

        if (alreadyExists) {
            throw new DuplicatePracticeDayException();
        }

        practiceDays.add(practiceDay);
    }


    //removePracticeDay
    public void removePracticeDay(DayOfWeek dayOfWeek, LocalTime time) {
        Objects.requireNonNull(dayOfWeek, "Day of week cannot be null");
        Objects.requireNonNull(time, "Time cannot be null");

        practiceDays.removeIf(practiceDay ->
                practiceDay.getDayOfWeek() == dayOfWeek
                    && practiceDay.getTime().equals(time)
        );

    }

    //activate
    public void activate() {
        this.active = true;
    }

    //desactivate
    public void deactivate() {
        this.active = false;
    }
}
