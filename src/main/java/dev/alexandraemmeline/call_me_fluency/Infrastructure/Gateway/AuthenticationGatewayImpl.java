package dev.alexandraemmeline.call_me_fluency.Infrastructure.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.InvalidCredentialsException;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.UserNotFoundException;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.AuthenticationGateway;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationGatewayImpl implements AuthenticationGateway {

    private final AuthenticationManager authenticationManager;
    private final UserRepositoryGateway userRepositoryGateway;


    @Override
    public UserDomain authenticate(String email, String password) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }

        UserDomain userDomain = userRepositoryGateway.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException());

        return userDomain;
    }

}
