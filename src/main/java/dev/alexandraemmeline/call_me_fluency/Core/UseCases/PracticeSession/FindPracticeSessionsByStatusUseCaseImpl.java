package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.PracticeSessionStatus;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;

import java.util.Set;

public class FindPracticeSessionsByStatusUseCaseImpl implements FindPracticeSessionsByStatusUseCase {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;

    public FindPracticeSessionsByStatusUseCaseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
    }


    @Override
    public Set<PracticeSessionDomain> execute(PracticeSessionStatus status) {
        return practiceSessionRepositoryGateway.findByStatus(status);
    }
}
