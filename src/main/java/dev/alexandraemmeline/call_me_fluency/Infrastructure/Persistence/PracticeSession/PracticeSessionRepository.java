package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSession;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PracticeSessionRepository extends JpaRepository<PracticeSessionEntity, Long> {

    Optional<PracticeSessionEntity> findById(Long practiceSessionId);

}
