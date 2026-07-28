package dev.alexandraemmeline.call_me_fluency.Infrastructure.Config.Bootstrap;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.RoleName;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.UserLevel;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.UserStatus;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.RoleEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.RoleRepository;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.UserEntity;
import dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AdminBootstrapService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${bootstrap.admin.name}")
    private String name;

    @Value("${bootstrap.admin.email}")
    private String email;

    @Value("${bootstrap.admin.password}")
    private String password;


    @PostConstruct
    public void createAdminIfNotExists() {

        if (userRepository.existsByEmail(email)) {
            return;
        }

        RoleEntity adminRole = roleRepository
                .findByName(RoleName.ROLE_ADMIN)
                .orElseThrow();

        UserEntity admin = new UserEntity();

        admin.setName(name);
        admin.setEmail(email);
        admin.setPasswordHash(passwordEncoder.encode(password));

        admin.setCreatedAt(LocalDateTime.now());
        admin.setUserLevel(UserLevel.ADVANCED);
        admin.setUserStatus(UserStatus.ACTIVE);

        admin.getRoles().add(adminRole);

        userRepository.save(admin);
    }

}
