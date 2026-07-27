package dev.alexandraemmeline.call_me_fluency.Core.UseCases;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidCredentialsException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.AuthenticationGateway;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.TokenProviderGateway;


public class LoginUseCaseImpl implements LoginUseCase {

    private final AuthenticationGateway authenticationGateway;
    private final TokenProviderGateway tokenProviderGateway;

    public LoginUseCaseImpl(AuthenticationGateway authenticationGateway, TokenProviderGateway tokenProviderGateway) {
        this.authenticationGateway = authenticationGateway;
        this.tokenProviderGateway = tokenProviderGateway;
    }


    @Override
    public String execute(String email, String password) {

            UserDomain authenticatedUser = authenticationGateway.authenticate(email, password);

            return tokenProviderGateway.generateToken(authenticatedUser);

    }

}
