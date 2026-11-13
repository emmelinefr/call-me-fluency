package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;

import java.util.Set;

public class ListPracticeSessionsUseCaseImpl implements ListPracticeSessionsUseCase {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;

    public ListPracticeSessionsUseCaseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
    }

    @Override
    public Set<PracticeSessionDomain> execute(Long userId) {

        return practiceSessionRepositoryGateway.findByUserId(userId);

    }
}
