package dev.alexandraemmeline.call_me_fluency.Core.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;

public interface UserRepositoryGateway {

    boolean existsByEmail(String email);

    UserDomain createUser(UserDomain userDomain);

}
