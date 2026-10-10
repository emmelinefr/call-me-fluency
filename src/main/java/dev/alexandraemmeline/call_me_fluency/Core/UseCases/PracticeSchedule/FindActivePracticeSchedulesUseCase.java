package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;

import java.util.Set;

public interface FindActivePracticeSchedulesUseCase {

    Set<PracticeScheduleDomain> execute();

}
