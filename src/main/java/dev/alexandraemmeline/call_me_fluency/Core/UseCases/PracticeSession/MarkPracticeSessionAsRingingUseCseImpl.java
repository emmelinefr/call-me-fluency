package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeSessionNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;

public class MarkPracticeSessionAsRingingUseCseImpl implements MarkPracticeSessionAsRingingUseCse {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;

    public MarkPracticeSessionAsRingingUseCseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
    }


    @Override
    public PracticeSessionDomain execute(Long practiceSessionId) {

        PracticeSessionDomain practiceSessionDomain = practiceSessionRepositoryGateway.findById(practiceSessionId)
                .orElseThrow(() -> new PracticeSessionNotFoundException());

        practiceSessionDomain.markAsRinging();

        return practiceSessionRepositoryGateway.save(practiceSessionDomain);

    }
}
