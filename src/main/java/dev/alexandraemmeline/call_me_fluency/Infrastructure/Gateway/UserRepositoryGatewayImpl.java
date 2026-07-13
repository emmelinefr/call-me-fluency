package dev.alexandraemmeline.call_me_fluency.Infrastructure.Gateway;

import dev.alexandraemmeline.call_me_fluency.Core.Domains.UserDomain;
import dev.alexandraemmeline.call_me_fluency.Core.Gateway.UserRepositoryGateway;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Mappers.UserMapper;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.UserEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryGatewayImpl implements UserRepositoryGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public UserDomain createUser(UserDomain userDomain) {
        UserEntity userEntity = userMapper.toEntity(userDomain);
        UserEntity savedEntity = userRepository.save(userEntity);

        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<UserDomain> findByEmail(String email) {

        return userRepository.findByEmail(email)
                .map(userMapper::toDomain);

    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }


    @Override
    public List<UserDomain> listUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toDomain)
                .toList();

    }

    @Override
    public void update(UserDomain user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userRepository.save(userEntity);

    }
}
