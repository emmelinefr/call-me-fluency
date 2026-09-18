package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;

import java.util.Set;

public class CreatePracticeScheduleUseCaseImpl implements CreatePracticeScheduleUseCase {

    private final PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway;

    public CreatePracticeScheduleUseCaseImpl(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        this.practiceScheduleRepositoryGateway = practiceScheduleRepositoryGateway;
    }

    @Override
    public PracticeScheduleDomain execute(UserDomain user, Set<PracticeDayDomain> practiceDays) {

        PracticeScheduleDomain practiceSchedule = new PracticeScheduleDomain(user);

        practiceDays.forEach(practiceSchedule::addPracticeDay);

        return practiceScheduleRepositoryGateway.save(practiceSchedule);
    }

}
