package dev.alexandraemmeline.call_me_fluency.Core;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionAlreadyExistsException;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.FindActivePracticeSchedulesUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.CreatePracticeSessionUseCase;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

public class PracticeSessionManager {

    private final FindActivePracticeSchedulesUseCase findActivePracticeSchedulesUseCase;
    private final CreatePracticeSessionUseCase createPracticeSessionUseCase;

    public PracticeSessionManager(FindActivePracticeSchedulesUseCase findActivePracticeSchedulesUseCase, CreatePracticeSessionUseCase createPracticeSessionUseCase) {
        this.findActivePracticeSchedulesUseCase = findActivePracticeSchedulesUseCase;
        this.createPracticeSessionUseCase = createPracticeSessionUseCase;
    }


    public void manageSessions() {

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



}
