package dev.alexandraemmeline.call_me_fluency.Infrastructure.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.TokenProviderGateway;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Config.Security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProviderGatewayImpl implements TokenProviderGateway {

    private final TokenProvider tokenProvider;

    @Override
    public String generateToken(UserDomain userDomain) {
        return tokenProvider.generateToken(userDomain);
    }

}
