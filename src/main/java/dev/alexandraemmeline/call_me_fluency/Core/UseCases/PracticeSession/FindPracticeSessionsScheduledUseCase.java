package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;

import java.util.Set;

public interface FindPracticeSessionsScheduledUseCase {

    Set<PracticeSessionDomain> execute();

}
