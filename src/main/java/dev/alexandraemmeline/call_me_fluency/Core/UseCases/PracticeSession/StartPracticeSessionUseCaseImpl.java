package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;

public class StartPracticeSessionUseCaseImpl implements StartPracticeSessionUseCase {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;

    public StartPracticeSessionUseCaseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
    }


    @Override
    public PracticeSessionDomain execute(Long practiceSessionId) {

        PracticeSessionDomain practiceSessionDomain = practiceSessionRepositoryGateway.findById(practiceSessionId)
                .orElseThrow(() -> new PracticeSessionNotFoundException());

        practiceSessionDomain.startSession();

        return practiceSessionRepositoryGateway.save(practiceSessionDomain);
    }
}
