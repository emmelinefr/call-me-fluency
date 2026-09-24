package dev.alexandraemmeline.call_me_fluency.Infrastructure.Config.Application;

import dev.alexandraemmeline.call_me_fluency.Core.Gateway.AuthenticationGateway;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.PracticeScheduleRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.TokenProviderGateway;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.CreatePracticeScheduleUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.CreatePracticeScheduleUseCaseImpl;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.FindPracticeScheduleByUserIdUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.PracticeSchedule.FindPracticeScheduleByUserIdUseCaseImpl;
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
    public CreatePracticeScheduleUseCase createPracticeScheduleUseCase(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        return new CreatePracticeScheduleUseCaseImpl(practiceScheduleRepositoryGateway);
    }

    @Bean
    public FindPracticeScheduleByUserIdUseCase findPracticeScheduleByIdUseCase(PracticeScheduleRepositoryGateway practiceScheduleRepositoryGateway) {
        return new FindPracticeScheduleByUserIdUseCaseImpl(practiceScheduleRepositoryGateway);
    }
}
