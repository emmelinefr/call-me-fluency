package dev.alexandraemmeline.call_me_fluency.Core.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;

import java.util.Optional;
import java.util.Set;

public interface PracticeScheduleRepositoryGateway {

    PracticeScheduleDomain save(PracticeScheduleDomain practiceScheduleDomain);

    boolean existsByUserId(Long id);

    Optional<PracticeScheduleDomain> findByUserId(Long id);

    void deleteByUserId(Long userId);

    Optional<PracticeScheduleDomain> findById(Long id);

    Set<PracticeScheduleDomain> findActives();

}
