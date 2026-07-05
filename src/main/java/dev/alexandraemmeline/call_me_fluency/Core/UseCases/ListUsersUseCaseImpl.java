package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;

import java.util.List;

public class ListUsersUseCaseImpl implements ListUsersUseCase {

    private final UserRepositoryGateway userRepositoryGateway;
    public ListUsersUseCaseImpl(UserRepositoryGateway userRepositoryGateway) {
        this.userRepositoryGateway = userRepositoryGateway;
    }


    @Override
    public List<UserDomain> execute() {
        return userRepositoryGateway.listUsers();
    }

}
