package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "practice_schedule")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PracticeScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //practice_schedule & user
    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private UserEntity user;


    //practice_schedule & practice_day
    @ElementCollection
    @CollectionTable(
            name = "practice_days",
            joinColumns = @JoinColumn(name = "practice_schedule_id")
    )
    private Set<PracticeDayEntity> practiceDays;

    @Column(nullable = false)
    private boolean active;

}
