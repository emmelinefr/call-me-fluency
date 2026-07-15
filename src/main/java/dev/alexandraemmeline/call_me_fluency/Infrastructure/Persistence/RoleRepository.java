package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(RoleName roleName);

}

