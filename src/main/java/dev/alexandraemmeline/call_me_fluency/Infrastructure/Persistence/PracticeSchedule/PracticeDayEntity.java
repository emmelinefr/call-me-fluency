package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.PracticeSchedule;


import dev.alexandraemmeline.call_me_fluency.Core.Enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PracticeDayEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private int duration;

}
