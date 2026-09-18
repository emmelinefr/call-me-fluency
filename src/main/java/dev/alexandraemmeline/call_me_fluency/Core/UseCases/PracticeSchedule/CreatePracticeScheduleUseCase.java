package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;

import java.util.Set;

public interface CreatePracticeScheduleUseCase {

    PracticeScheduleDomain execute(UserDomain user, Set<PracticeDayDomain> practiceDays);

}
