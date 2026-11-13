package dev.alexandraemmeline.call_me_fluency.Core.Managers;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.PracticeSessionStatus;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidPracticeSessionStateException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionAlreadyExistsException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.FindActivePracticeSchedulesUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class PracticeSessionManager {

    private final FindActivePracticeSchedulesUseCase findActivePracticeSchedulesUseCase;
    private final CreatePracticeSessionUseCase createPracticeSessionUseCase;
    private final FindPracticeSessionsByStatusUseCase findPracticeSessionsByStatusUseCase;
    private final MarkPracticeSessionAsRingingUseCase markPracticeSessionAsRingingUseCase;
    private final CancelPracticeSessionUseCase cancelPracticeSessionUseCase;
    private final MarkPracticeSessionAsMissedUseCase markPracticeSessionAsMissedUseCase;

    private static final int RING_TIMEOUT_MINUTES = 5;

    public PracticeSessionManager(FindActivePracticeSchedulesUseCase findActivePracticeSchedulesUseCase, CreatePracticeSessionUseCase createPracticeSessionUseCase, FindPracticeSessionsByStatusUseCase findPracticeSessionsByStatusUseCase, MarkPracticeSessionAsRingingUseCase markPracticeSessionAsRingingUseCase, CancelPracticeSessionUseCase cancelPracticeSessionUseCase, MarkPracticeSessionAsMissedUseCase markPracticeSessionAsMissedUseCase) {
        this.findActivePracticeSchedulesUseCase = findActivePracticeSchedulesUseCase;
        this.createPracticeSessionUseCase = createPracticeSessionUseCase;
        this.findPracticeSessionsByStatusUseCase = findPracticeSessionsByStatusUseCase;
        this.markPracticeSessionAsRingingUseCase = markPracticeSessionAsRingingUseCase;
        this.cancelPracticeSessionUseCase = cancelPracticeSessionUseCase;
        this.markPracticeSessionAsMissedUseCase = markPracticeSessionAsMissedUseCase;
    }


    // MARK AS SCHEDULED
    public void scheduleSessions() {

        Set<PracticeScheduleDomain> activePracticeSchedules = findActivePracticeSchedulesUseCase.execute();

        LocalDateTime now = LocalDateTime.now()
                .withSecond(0)
                .withNano(0);
        LocalDateTime windowEnd = now.plusMinutes(5);
        DayOfWeek today = now.getDayOfWeek();


        activePracticeSchedules.forEach(schedule -> {

            schedule.getPracticeDays().forEach(practiceDay -> {

                if (practiceDay.getDayOfWeek() != dev.alexandraemmeline.call_me_fluency.Core.Enums.DayOfWeek.valueOf(today.name())) {
                    return;
                }

                LocalDateTime scheduledAt = now.toLocalDate().atTime(practiceDay.getTime());

                if (scheduledAt.isBefore(now) || scheduledAt.isAfter(windowEnd)) {
                    return;
                }


                try {
                    createPracticeSessionUseCase.execute(schedule.getUserDomain().getId(), scheduledAt);
                } catch (PracticeSessionAlreadyExistsException ex) {
                    //The practice session already exists; Continue processing the remainig practices.
                }
            });
        });

    }


    // MARK AS RINGING
    public void ringSessions() {

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        findPracticeSessionsByStatusUseCase.execute(PracticeSessionStatus.SCHEDULED)
                .forEach(session -> {
                    try {
                        if (!session.getPracticeSchedule().isActive()) {
                            return;
                        }

                        if (session.getScheduledAt().isAfter(now)) {
                            return;
                        }

                        markPracticeSessionAsRingingUseCase.execute(session.getId());
                        // Phase 2: this is where the notification to the user (WebSocket) comes in
                    } catch (PracticeSessionNotFoundException ex) {
                        // Session was already processed or no longer exists. Continue with the remaining sessions.
                    }

                });

    }

    // MARK AS MISSED OR CANCELED
    public void expireSessions() {

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

        findPracticeSessionsByStatusUseCase.execute(PracticeSessionStatus.SCHEDULED)
                .forEach(session -> {
                            try {
                                if (!session.getPracticeSchedule().isActive()) {
                                    cancelPracticeSessionUseCase.execute(session.getId());
                                }
                            } catch (InvalidPracticeSessionStateException | PracticeSessionNotFoundException ex) {
                                // Session was already processed or its state changed. Continue with the remaining sessions.
                            }
                        });



        findPracticeSessionsByStatusUseCase.execute(PracticeSessionStatus.RINGING)
                .forEach(session -> {
                    try {
                        LocalDateTime limit = session.getScheduledAt().plusMinutes(RING_TIMEOUT_MINUTES);

                        if (!limit.isAfter(now)) {
                            markPracticeSessionAsMissedUseCase.execute(session.getId());
                        }

                    } catch (InvalidPracticeSessionStateException | PracticeSessionNotFoundException ex) {
                        // Session was already processed or no longer exists. Continue with the remaining sessions.
                    }
                });

    }
}
