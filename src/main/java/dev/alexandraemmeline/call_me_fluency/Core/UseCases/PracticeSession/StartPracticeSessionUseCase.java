package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;

public interface StartPracticeSessionUseCase {

    PracticeSessionDomain execute(Long practiceSessionId);

}
