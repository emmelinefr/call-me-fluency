package dev.alexandraemmeline.call_me_fluency.Infrastructure.Config.Application;

import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryGateway userRepositoryGateway, PasswordEncoder passwordEncoder) {
        return new CreateUserUseCaseImpl(userRepositoryGateway,passwordEncoder);
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

}
