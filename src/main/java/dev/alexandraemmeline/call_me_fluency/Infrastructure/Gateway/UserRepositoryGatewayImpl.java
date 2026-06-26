package dev.alexandraemmeline.call_me_fluency.Infrastructure.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.UserMapper;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryGatewayImpl implements UserRepositoryGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

}
