package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.UserNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;

public class PromoteUserToAdminUseCaseImpl implements PromoteUserToAdminUseCase {

    private final UserRepositoryGateway userRepositoryGateway;

    public PromoteUserToAdminUseCaseImpl(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }

    @Override
    public void execute(Long id) {

        UserDomain userDomain = userRepositoryGateway.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        userDomain.promoteToAdmin();

        userRepositoryGateway.save(userDomain);
    }

}
