package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule;

import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeScheduleNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;

public class DeletePracticeScheduleUseCaseImpl implements DeletePracticeScheduleUseCase {

    private final PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway;

    public DeletePracticeScheduleUseCaseImpl(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        this.practiceScheduleRepositoryGateway = practiceScheduleRepositoryGateway;
    }



    @Override
    public void execute(Long userId) {

        practiceScheduleRepositoryGateway.findByUserId(userId)
                .orElseThrow(() -> new PracticeScheduleNotFoundException());

        practiceScheduleRepositoryGateway.deleteByUserId(userId);
    }
}
