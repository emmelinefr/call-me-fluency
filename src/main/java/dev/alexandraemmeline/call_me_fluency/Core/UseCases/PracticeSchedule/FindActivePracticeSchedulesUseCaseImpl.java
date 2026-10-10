package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;

import java.util.Set;

public class FindActivePracticeSchedulesUseCaseImpl implements FindActivePracticeSchedulesUseCase {

    private final PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway;

    public FindActivePracticeSchedulesUseCaseImpl(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        this.practiceScheduleRepositoryGateway = practiceScheduleRepositoryGateway;
    }


    @Override
    public Set<PracticeScheduleDomain> execute() {
        return practiceScheduleRepositoryGateway.findActives();
    }
}
