package dev.alexandraemmeline.call_me_fluency.Infrastructure.Scheduling;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.PracticeSessionStatus;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.CancelPracticeSessionUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.FindPracticeSessionsByStatusUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.MarkPracticeSessionAsMissedUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PracticeSessionExpiration {

    private static final int RING_TIMEOUT_MINUTES = 5;

    private final FindPracticeSessionsByStatusUseCase findPracticeSessionsByStatusUseCase;
    private final CancelPracticeSessionUseCase cancelPracticeSessionUseCase;
    private final MarkPracticeSessionAsMissedUseCase markPracticeSessionAsMissedUseCase;

    public PracticeSessionExpiration(FindPracticeSessionsByStatusUseCase findPracticeSessionsByStatusUseCase, CancelPracticeSessionUseCase cancelPracticeSessionUseCase, MarkPracticeSessionAsMissedUseCase markPracticeSessionAsMissedUseCase) {
        this.findPracticeSessionsByStatusUseCase = findPracticeSessionsByStatusUseCase;
        this.cancelPracticeSessionUseCase = cancelPracticeSessionUseCase;
        this.markPracticeSessionAsMissedUseCase = markPracticeSessionAsMissedUseCase;
    }

    @Scheduled(fixedDelay = 60000)
    public void expireSessions() {

        LocalDateTime now = LocalDateTime.now()
                .withSecond(0)
                .withNano(0);

        findPracticeSessionsByStatusUseCase.execute(PracticeSessionStatus.SCHEDULED)
                .forEach(session -> {
                    try {
                        if (!session.getPracticeSchedule().isActive()) {
                            cancelPracticeSessionUseCase.execute(session.getId());
                        }
                    } catch (PracticeSessionNotFoundException ex) {

                    } catch (Exception ex) {
                        System.out.println("Failed to cancel session. Error: " + ex);
                    }

                });
        

        findPracticeSessionsByStatusUseCase.execute(PracticeSessionStatus.RINGING)
                .forEach(session -> {
                    try {
                        LocalDateTime limit = session.getScheduledAt().plusMinutes(RING_TIMEOUT_MINUTES);
                        if (limit.isBefore(now)) {
                            markPracticeSessionAsMissedUseCase.execute(session.getId());
                        }
                    } catch (PracticeSessionNotFoundException ex) {

                    } catch (Exception ex) {
                        System.out.println("Failed to mark session as missesd. Error: " + ex);
                    }

                });

    }

}
