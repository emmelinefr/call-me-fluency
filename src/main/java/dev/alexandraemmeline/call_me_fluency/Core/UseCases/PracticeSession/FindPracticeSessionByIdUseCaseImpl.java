package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;

public class FindPracticeSessionByIdUseCaseImpl implements FindPracticeSessionByIdUseCase {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;

    public FindPracticeSessionByIdUseCaseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
    }


    @Override
    public PracticeSessionDomain execute(Long practiceSessionId) {
        return practiceSessionRepositoryGateway.findById(practiceSessionId)
                .orElseThrow(() -> new PracticeSessionNotFoundException());
    }

}
