package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeScheduleNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;

public class FindPracticeScheduleByUserIdUseCaseImpl implements FindPracticeScheduleByUserIdUseCase {

    private final PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway;

    public FindPracticeScheduleByUserIdUseCaseImpl(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        this.practiceScheduleRepositoryGateway = practiceScheduleRepositoryGateway;
    }


    @Override
    public PracticeScheduleDomain execute(Long id) {

        return practiceScheduleRepositoryGateway
                .findByUserId(id)
                .orElseThrow(() -> new PracticeScheduleNotFoundException());

    }
}
