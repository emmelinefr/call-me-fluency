package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PracticeScheduleRepository extends JpaRepository<PracticeScheduleEntity, Long> {

    boolean existsByUserId(Long id);

    Optional<PracticeScheduleEntity> findByUserId(Long id);
}
