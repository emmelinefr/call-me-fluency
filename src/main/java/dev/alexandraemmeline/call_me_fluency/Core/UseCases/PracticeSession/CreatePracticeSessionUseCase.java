package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;

import java.time.LocalDateTime;

public interface CreatePracticeSessionUseCase {

    PracticeSessionDomain execute(Long userId, LocalDateTime scheduledAt);

}
