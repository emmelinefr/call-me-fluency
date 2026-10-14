package dev.alexandraemmeline.call_me_fluency.Infrastructure.Scheduling;

import dev.alexandraemmeline.call_me_fluency.Core.Managers.PracticeSessionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PracticeSessionScheduler {

    private final PracticeSessionManager practiceSessionManager;

    public PracticeSessionScheduler(PracticeSessionManager practiceSessionManager) {
        this.practiceSessionManager = practiceSessionManager;
    }


    @Scheduled(fixedDelay = 60000)
    public void schedulePracticeSessions() {
        practiceSessionManager.manageSessions();
    }

}
