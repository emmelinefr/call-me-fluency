package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface PracticeScheduleRepository extends JpaRepository<PracticeScheduleEntity, Long> {

    boolean existsByUserId(Long id);

    Optional<PracticeScheduleEntity> findByUserId(Long id);

    void deleteByUserId(Long userId);

    Optional<PracticeScheduleEntity> findById(Long id);

    @Query(value = """
                    SELECT * 
                    FROM practice_schedule
                    WHERE active = TRUE
                   """, nativeQuery = true)
    Set<PracticeScheduleEntity> findActives();
}
