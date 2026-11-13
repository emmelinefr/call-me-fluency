package dev.alexandraemmeline.call_me_fluency.Infrastructure.Jobs;

import dev.alexandraemmeline.call_me_fluency.Core.Managers.PracticeSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PracticeSessionScheduler {

    private final PracticeSessionManager practiceSessionManager;

    public PracticeSessionScheduler(PracticeSessionManager practiceSessionManager) {
        this.practiceSessionManager = practiceSessionManager;
    }


    @Scheduled(fixedDelay = 60000)
    public void schedulePracticeSessions() {
        try {
            practiceSessionManager.scheduleSessions();

        } catch (Exception ex) {
            log.error("Failed to execute practice session scheduling job.", ex);
        }
    }

}
