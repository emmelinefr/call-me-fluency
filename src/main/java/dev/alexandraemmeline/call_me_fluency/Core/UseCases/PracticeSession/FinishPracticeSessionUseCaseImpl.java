package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;

public class FinishPracticeSessionUseCaseImpl implements FinishPracticeSessionUseCase {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;

    public FinishPracticeSessionUseCaseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
    }


    @Override
    public PracticeSessionDomain execute(Long practiceSessionId) {

        PracticeSessionDomain practiceSession = practiceSessionRepositoryGateway.findById(practiceSessionId)
                .orElseThrow(() -> new PracticeSessionNotFoundException());

        practiceSession.finishSession();

        return practiceSessionRepositoryGateway.save(practiceSession);
    }
}
