package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;

import java.util.Set;

public class FindPracticeSessionsScheduledUseCaseImpl implements FindPracticeSessionsScheduledUseCase {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;

    public FindPracticeSessionsScheduledUseCaseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
    }


    @Override
    public Set<PracticeSessionDomain> execute() {
        return practiceSessionRepositoryGateway.findScheduleds();
    }
}
