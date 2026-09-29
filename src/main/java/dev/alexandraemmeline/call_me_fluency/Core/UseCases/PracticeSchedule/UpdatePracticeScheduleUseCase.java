package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayKey;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;

import java.util.Set;

public interface UpdatePracticeScheduleUseCase {

    PracticeScheduleDomain execute(Long userId, boolean active, Set<PracticeDayDomain> daysToAdd, Set<PracticeDayKey> daysToRemove);

}
