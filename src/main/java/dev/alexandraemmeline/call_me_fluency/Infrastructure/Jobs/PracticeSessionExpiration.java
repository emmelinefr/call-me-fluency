package dev.alexandraemmeline.call_me_fluency.Infrastructure.Jobs;

import dev.alexandraemmeline.call_me_fluency.Core.Managers.PracticeSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PracticeSessionExpiration {

    private final PracticeSessionManager practiceSessionManager;

    public PracticeSessionExpiration(PracticeSessionManager practiceSessionManager) {
        this.practiceSessionManager = practiceSessionManager;
    }


    @Scheduled(fixedDelay = 60000)
    public void expireSessions() {

        try {
            practiceSessionManager.expireSessions();

        } catch (Exception ex) {
            log.error("Failed to execute practice session expiration job.", ex);
        }

    }

}
