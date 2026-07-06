package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.UserNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;

public class FindUserByEmailUseCaseImpl implements FindUserByEmailUseCase {

    private final UserRepositoryGateway userRepositoryGateway;
    public FindUserByEmailUseCaseImpl(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }


    @Override
    public UserDomain execute(String email) {

        UserDomain user = userRepositoryGateway.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());

        return user;
    }

}
