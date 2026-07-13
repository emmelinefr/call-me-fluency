package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidCredentialsException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.SamePasswordException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.UserNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {

    private final UserRepositoryGateway userRepositoryGateway;
    private final PasswordEncoder passwordEncoder;

    public ChangePasswordUseCaseImpl(UserRepositoryGateway userRepositoryGateway, PasswordEncoder passwordEncoder) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void execute(String email, String currentPassword, String newPassword) {

        UserDomain user = userRepositoryGateway.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());


        if (!passwordEncoder.matches(currentPassword, user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        if (passwordEncoder.matches(newPassword, user.getPasswordHash())) {
            throw new SamePasswordException();
        }

        String newPasswordHash = passwordEncoder.encode(newPassword);

        user.changePasswordHash(newPasswordHash);

        userRepositoryGateway.update(user);

    }
}
