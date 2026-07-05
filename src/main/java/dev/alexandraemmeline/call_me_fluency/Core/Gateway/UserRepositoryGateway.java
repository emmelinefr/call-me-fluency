package dev.alexandraemmeline.call_me_fluency.Core.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryGateway {

    boolean existsByEmail(String email);

    UserDomain createUser(UserDomain userDomain);

    Optional<UserDomain> findByEmail(String email);

    void deleteByEmail(String email);

    List<UserDomain> listUsers();

}
