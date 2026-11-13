package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.PracticeSessionStatus;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule.PracticeScheduleEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "practice_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PracticeSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //practice_session & user
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private UserEntity user;

    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PracticeSessionStatus status;


    //practice_session & practice_schedule
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "practice_schedule_id",
            nullable = false
    )
    private PracticeScheduleEntity practiceSchedule;

}
