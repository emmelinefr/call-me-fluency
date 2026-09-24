package dev.alexandraemmeline.call_me_fluency.Core.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;

import java.util.Optional;

public interface PracticeScheduleRepositoryGateway {

    PracticeScheduleDomain save(PracticeScheduleDomain practiceScheduleDomain);

    boolean existsByUserId(Long id);

    Optional<PracticeScheduleDomain> findByUserId(Long id);

}
