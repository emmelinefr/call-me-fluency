package dev.alexandraemmeline.call_me_fluency.Core.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;

import java.util.Optional;

public interface PracticeSessionRepositoryGateway {

    Optional<PracticeSessionDomain> findById(Long practiceSessionId);

    PracticeSessionDomain save(PracticeSessionDomain practiceSessionDomain);

}
