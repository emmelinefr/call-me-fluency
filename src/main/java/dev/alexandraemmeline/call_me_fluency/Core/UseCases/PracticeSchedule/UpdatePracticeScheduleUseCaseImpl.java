package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeDayKey;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeScheduleNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.UserNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;

import java.util.Set;

public class UpdatePracticeScheduleUseCaseImpl implements UpdatePracticeScheduleUseCase {

    private final PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway;

    public UpdatePracticeScheduleUseCaseImpl(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        this.practiceScheduleRepositoryGateway = practiceScheduleRepositoryGateway;
    }


    @Override
    public PracticeScheduleDomain execute(Long userId, boolean active, Set<PracticeDayDomain> daysToAdd, Set<PracticeDayKey> daysToRemove) {

        PracticeScheduleDomain practiceScheduleDomain =
                practiceScheduleRepositoryGateway
                        .findByUserId(userId)
                        .orElseThrow(() -> new PracticeScheduleNotFoundException());

        daysToRemove.forEach(day ->
                practiceScheduleDomain.removePracticeDay(
                        day.dayOfWeek(),
                        day.time()
                ));

        daysToAdd.forEach(
                practiceScheduleDomain::addPracticeDay);

        if (active) {
            practiceScheduleDomain.activate();
        } else {
            practiceScheduleDomain.deactivate();
        }

        return practiceScheduleRepositoryGateway.save(practiceScheduleDomain);
    }
}
