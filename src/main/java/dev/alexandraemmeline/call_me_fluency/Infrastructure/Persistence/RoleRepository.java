package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}

