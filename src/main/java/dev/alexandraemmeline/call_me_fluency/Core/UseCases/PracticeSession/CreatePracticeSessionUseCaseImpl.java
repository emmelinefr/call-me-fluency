package dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeScheduleDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.PracticeSessionDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.PracticeScheduleNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.UserNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeSessionRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;

import java.time.LocalDateTime;

public class CreatePracticeSessionUseCaseImpl implements CreatePracticeSessionUseCase {

    private final PracticeSessionRepositoryGateway practiceSessionRepositoryGateway;
    private final UserRepositoryGateway userRepositoryGateway;
    private final PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway;

    public CreatePracticeSessionUseCaseImpl(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway, UserRepositoryGateway userRepositoryGateway, PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        this.practiceSessionRepositoryGateway = practiceSessionRepositoryGateway;
        this.userRepositoryGateway = userRepositoryGateway;
        this.practiceScheduleRepositoryGateway = practiceScheduleRepositoryGateway;
    }

    @Override
    public PracticeSessionDomain execute(Long userId, LocalDateTime scheduledAt) {

        UserDomain user = userRepositoryGateway.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());


        PracticeScheduleDomain practiceSchedule = practiceScheduleRepositoryGateway.findByUserId(user.getId())
                .orElseThrow(() -> new PracticeScheduleNotFoundException());


        PracticeSessionDomain practiceSession = new PracticeSessionDomain(
                user,
                scheduledAt,
                practiceSchedule
        );

        return practiceSessionRepositoryGateway.save(practiceSession);

    }
}
