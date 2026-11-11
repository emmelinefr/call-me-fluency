package dev.alexandraemmeline.call_me_fluency.Infrastructure.Jobs;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.PracticeSessionStatus;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidPracticeSessionStateException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.FindPracticeSessionsByStatusUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.MarkPracticeSessionAsRingingUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class PracticeSessionRinging {

    private final FindPracticeSessionsByStatusUseCase findPracticeSessionsByStatusUseCase;
    private final MarkPracticeSessionAsRingingUseCase markPracticeSessionAsRingingUseCase;

    public PracticeSessionRinging(FindPracticeSessionsByStatusUseCase findPracticeSessionsByStatusUseCase, MarkPracticeSessionAsRingingUseCase markPracticeSessionAsRingingUseCase) {
        this.findPracticeSessionsByStatusUseCase = findPracticeSessionsByStatusUseCase;
        this.markPracticeSessionAsRingingUseCase = markPracticeSessionAsRingingUseCase;
    }


    @Scheduled(fixedDelay = 60000)
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
                        log.info("Session {} is now RINGING.", session.getId());
                        // Phase 2: this is where the notification to the user (WebSocket) comes in
                    } catch (InvalidPracticeSessionStateException | PracticeSessionNotFoundException ex) {
                        log.warn("Session skiped {}", ex.getMessage());
                    } catch (Exception ex) {
                        log.error("Failed to mark session {} as ringing", session.getId(), ex);
                    }
                });

    }

}
