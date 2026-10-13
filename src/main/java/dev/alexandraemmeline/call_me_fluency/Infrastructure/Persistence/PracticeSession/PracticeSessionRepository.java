package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface PracticeSessionRepository extends JpaRepository<PracticeSessionEntity, Long> {

    Optional<PracticeSessionEntity> findById(Long practiceSessionId);

    Set<PracticeSessionEntity> findByUserId(Long userId);

    @Query(value = """
            SELECT EXISTS(
            SELECT  1
            FROM practice_sessions
            WHERE practice_schedule_id = :practiceScheduleId
            AND scheduled_at = :scheduledAt
            )
            """, nativeQuery = true)
    boolean existsByPracticeScheduleIdAndScheduledAt(@Param("practiceScheduleId") Long practiceScheduleId, @Param("scheduledAt") LocalDateTime scheduledAt);

}
