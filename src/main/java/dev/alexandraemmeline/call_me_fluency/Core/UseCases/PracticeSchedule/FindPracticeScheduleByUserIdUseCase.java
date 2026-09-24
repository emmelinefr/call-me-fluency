package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;

public interface FindPracticeScheduleByUserIdUseCase {

    PracticeScheduleDomain execute(Long id);

}
