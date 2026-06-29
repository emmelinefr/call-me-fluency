package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidCredentialsException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.UserNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import org.springframework.security.crypto.password.PasswordEncoder;


public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;
    private final PasswordEncoder passwordEncoder;

    public DeleteUserUseCaseImpl(UserRepositoryGateway userRepositoryGateway, PasswordEncoder passwordEncoder) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void execute(String email, String password) {

        UserDomain user = userRepositoryGateway.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        userRepositoryGateway.deleteByEmail(email);
    }
}
