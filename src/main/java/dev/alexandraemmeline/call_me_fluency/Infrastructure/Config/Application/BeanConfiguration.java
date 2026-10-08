package dev.alexandraemmeline.call_me_fluency.Infrastructure.Config.Application;

import dev.alexandraemmeline.call_me_fluency.Core.Gateway.*;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.*;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSession.*;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.User.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    //USER
    @Bean
    public RegisterUserUseCase createUserUseCase(UserRepositoryGateway userRepositoryGateway, PasswordEncoder passwordEncoder) {
        return new RegisterUserUseCaseImpl(userRepositoryGateway,passwordEncoder);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepositoryGateway userRepositoryGateway, PasswordEncoder passwordEncoder) {
        return new DeleteUserUseCaseImpl(userRepositoryGateway, passwordEncoder);
    }

    @Bean
    public ListUsersUseCase listUsersUseCase(UserRepositoryGateway userRepositoryGateway) {
        return new ListUsersUseCaseImpl(userRepositoryGateway);
    }

    @Bean
    public FindUserByEmailUseCase findUserByEmailUseCase(UserRepositoryGateway userRepositoryGateway) {
        return new FindUserByEmailUseCaseImpl(userRepositoryGateway);
    }

    @Bean
    public ChangePasswordUseCase changePasswordUseCase(UserRepositoryGateway userRepositoryGateway, PasswordEncoder passwordEncoder) {
        return new ChangePasswordUseCaseImpl(userRepositoryGateway, passwordEncoder);
    }

    @Bean
    public LoginUseCase loginUseCase(AuthenticationGateway authenticationGateway, TokenProviderGateway tokenProviderGateway) {
        return new LoginUseCaseImpl(authenticationGateway, tokenProviderGateway);
    }

    @Bean
    public PromoteUserToAdminUseCase promoteUserToAdminUseCase(UserRepositoryGateway userRepositoryGateway) {
        return new PromoteUserToAdminUseCaseImpl(userRepositoryGateway);
    }



    //PRACTICE SCHEDULE
    @Bean
    public CreatePracticeScheduleUseCase createPracticeSessionUseCase(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        return new CreatePracticeScheduleUseCaseImpl(practiceScheduleRepositoryGateway);
    }

    @Bean
    public FindPracticeScheduleByUserIdUseCase findPracticeScheduleByIdUseCase(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        return new FindPracticeScheduleByUserIdUseCaseImpl(practiceScheduleRepositoryGateway);
    }

    @Bean
    public UpdatePracticeScheduleUseCase updatePracticeScheduleUseCase(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        return new UpdatePracticeScheduleUseCaseImpl((practiceScheduleRepositoryGateway));
    }

    @Bean
    public DeletePracticeScheduleUseCase deletePracticeScheduleUseCase(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        return new DeletePracticeScheduleUseCaseImpl(practiceScheduleRepositoryGateway);
    }



    //PRACTICE SESSION
    @Bean
    public CreatePracticeSessionUseCase createPracticeSessionUseCase(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway, UserRepositoryGateway userRepositoryGateway, PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        return new CreatePracticeSessionUseCaseImpl(practiceSessionRepositoryGateway, userRepositoryGateway, practiceScheduleRepositoryGateway);
    }

    @Bean
    public StartPracticeSessionUseCase startPracticeSessionUseCase(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        return new StartPracticeSessionUseCaseImpl(practiceSessionRepositoryGateway);
    }

    @Bean
    public CancelPracticeSessionUseCase cancelPracticeSessionUseCase(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        return new CancelPracticeSessionUseCaseImpl(practiceSessionRepositoryGateway);
    }

    @Bean
    public FinishPracticeSessionUseCase finishPracticeSessionUseCase(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        return new FinishPracticeSessionUseCaseImpl(practiceSessionRepositoryGateway);
    }

    @Bean
    FindPracticeSessionByIdUseCase findPracticeSessionUseCase(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        return new FindPracticeSessionByIdUseCaseImpl(practiceSessionRepositoryGateway);
    }

    @Bean ListPracticeSessionsUseCase listPracticeSessionsUseCase(PracticeSessionRepositoryGateway practiceSessionRepositoryGateway) {
        return new ListPracticeSessionsUseCaseImpl(practiceSessionRepositoryGateway);
    }
}
