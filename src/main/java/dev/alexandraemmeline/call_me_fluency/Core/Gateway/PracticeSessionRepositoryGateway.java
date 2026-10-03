package dev.alexandraemmeline.call_me_fluency.Core.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;

import java.util.Optional;
import java.util.Set;

public interface PracticeSessionRepositoryGateway {

    Optional<PracticeSessionDomain> findById(Long practiceSessionId);

    PracticeSessionDomain save(PracticeSessionDomain practiceSessionDomain);

    Set<PracticeSessionDomain> findByUserId(Long userId);

}
