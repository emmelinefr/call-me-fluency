package dev.alexandraemmeline.call_me_fluency.Core.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;

public interface AuthenticationGateway {

    UserDomain authenticate(String email, String password);

}
