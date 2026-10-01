package dev.alexandraemmeline.call_me_fluency.Core.Domains;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.PracticeSessionStatus;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidPracticeSessionStateException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class PracticeSessionDomain {

    private Long id;
    private UserDomain user;
    private LocalDateTime scheduledAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private PracticeSessionStatus status;
    private PracticeScheduleDomain practiceSchedule;


    //constructor and its validations
    public PracticeSessionDomain(UserDomain user, LocalDateTime scheduledAt, PracticeScheduleDomain practiceSchedule) {
        this.user = Objects.requireNonNull(user, "User cannot be null");
        this.scheduledAt = Objects.requireNonNull(scheduledAt, "Scheduled time cannot be null");
        this.status = PracticeSessionStatus.SCHEDULED;
        this.practiceSchedule = Objects.requireNonNull(practiceSchedule, "Practice schedule cannot be null");
    }

    //reconstitute constructor
    public static PracticeSessionDomain reconstitute(Long id, UserDomain user, LocalDateTime scheduledAt, LocalDateTime startedAt, LocalDateTime endedAt, PracticeSessionStatus status, PracticeScheduleDomain practiceSchedule) {

        PracticeSessionDomain domain = new PracticeSessionDomain(user, scheduledAt, practiceSchedule);

        domain.id = id;
        domain.user = Objects.requireNonNull(user, "User cannot be null");
        domain.practiceSchedule = Objects.requireNonNull(practiceSchedule, "Practice schedule cannot be null");
        domain.scheduledAt = Objects.requireNonNull(scheduledAt, "Scheduled time cannot be null");
        domain.startedAt = startedAt;
        domain.endedAt = endedAt;
        domain.status = Objects.requireNonNull(status, "Status cannot be null");

        return domain;
    }

    //getters
    public Long getId() {
        return id;
    }

    public UserDomain getUser() {
        return user;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public Long getDurationInMinutes() {
        if (startedAt == null || endedAt == null) {
            return null;
        }

        return ChronoUnit.MINUTES.between(startedAt, endedAt);
    }

    public PracticeSessionStatus getStatus() {
        return status;
    }

    public PracticeScheduleDomain getPracticeSchedule() {
        return practiceSchedule;
    }


    //behaviors
    //start
    public void startSession() {
        if (status != PracticeSessionStatus.SCHEDULED) {
            throw new InvalidPracticeSessionStateException(
                    "Practice session cannot be started."
            );
        }

        this.startedAt = LocalDateTime.now();
        this.status = PracticeSessionStatus.IN_PROGRESS;

    }

    //finish
    public void finishSession() {
        if (status != PracticeSessionStatus.IN_PROGRESS) {
            throw new InvalidPracticeSessionStateException(
                    "Practice session cannot be finished."
            );
        }

        this.endedAt = LocalDateTime.now();
        this.status = PracticeSessionStatus.COMPLETED;

    }

    //cancel
    public void cancelSession() {
        if (status != PracticeSessionStatus.SCHEDULED) {
            throw new InvalidPracticeSessionStateException(
                    "Practice session cannot be canceled."
            );
        }

        this.status = PracticeSessionStatus.CANCELED;

    }

}
