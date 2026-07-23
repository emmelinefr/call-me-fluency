package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.RoleName;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.EmailAlreadyExistsException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepositoryGateway userRepositoryGateway;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCaseImpl(UserRepositoryGateway userRepositoryGateway, PasswordEncoder passwordEncoder) {
        this.userRepositoryGateway = userRepositoryGateway;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDomain execute(UserDomain userDomain) {

        if (userRepositoryGateway.existsByEmail(userDomain.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        String passworHash = passwordEncoder.encode(userDomain.getPasswordHash());
        userDomain.changePasswordHash(passworHash);

        userDomain.markAsCreated();
        userDomain.statusActivate();
        userDomain.addRole(RoleName.ROLE_USER);

        return userRepositoryGateway.createUser(userDomain);
    }

}
