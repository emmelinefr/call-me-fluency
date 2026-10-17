package dev.alexandraemmeline.call_me_fluency.Infrastructure.Scheduling;

import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.CancelPracticeSessionUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.FindPracticeSessionsScheduledUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PracticeSessionCancellation {

    private final FindPracticeSessionsScheduledUseCase findPracticeSessionsScheduledUseCase;
    private final CancelPracticeSessionUseCase cancelPracticeSessionUseCase;

    public PracticeSessionCancellation(FindPracticeSessionsScheduledUseCase findPracticeSessionsScheduledUseCase, CancelPracticeSessionUseCase cancelPracticeSessionUseCase) {
        this.findPracticeSessionsScheduledUseCase = findPracticeSessionsScheduledUseCase;
        this.cancelPracticeSessionUseCase = cancelPracticeSessionUseCase;
    }


    @Scheduled(fixedDelay = 60000)
    public void cancelSessions() {

        LocalDateTime now = LocalDateTime.now()
                .withSecond(0)
                .withNano(0);

        findPracticeSessionsScheduledUseCase.execute()
                .forEach(session -> {

                    LocalDateTime cancelationTime = session.getScheduledAt().plusMinutes(5);

                    if (!session.getPracticeSchedule().isActive()) {
                        cancelPracticeSessionUseCase.execute(session.getId());
                        
                    } else if (cancelationTime.isBefore(now)) {
                        cancelPracticeSessionUseCase.execute(session.getId());
                    }

                });

    }

}
