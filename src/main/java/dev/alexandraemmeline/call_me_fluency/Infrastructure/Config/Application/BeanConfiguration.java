package dev.alexandraemmeline.call_me_fluency.Infrastructure.Config;

import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.CreateUserUseCase;
import dev.alexandraemmeline.call_me_fluency.Core.UseCases.CreateUserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryGateway userRepositoryGateway, PasswordEncoder passwordEncoder) {
        return new CreateUserUseCaseImpl(userRepositoryGateway,passwordEncoder);
    }
}
