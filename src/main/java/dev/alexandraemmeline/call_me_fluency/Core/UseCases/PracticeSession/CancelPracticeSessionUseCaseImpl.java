package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;

public class CancelPracticeSessionUseCaseImpl implements CancelPracticeSessionUseCase {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;

    public CancelPracticeSessionUseCaseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
    }


    @Override
    public PracticeSessionDomain execute(Long practiceSessionId) {

        PracticeSessionDomain practiceSession = practiceSessionRepositoryGateway.findById(practiceSessionId)
                .orElseThrow(() -> new PracticeSessionNotFoundException());

        practiceSession.cancelSession();

        return practiceSessionRepositoryGateway.save(practiceSession);

    }
}
