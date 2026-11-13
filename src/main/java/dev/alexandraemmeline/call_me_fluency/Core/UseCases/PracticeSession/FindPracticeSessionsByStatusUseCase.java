package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.PracticeSessionStatus;

import java.util.Set;

public interface FindPracticeSessionsByStatusUseCase {

    Set<PracticeSessionDomain> execute(PracticeSessionStatus status);

}
