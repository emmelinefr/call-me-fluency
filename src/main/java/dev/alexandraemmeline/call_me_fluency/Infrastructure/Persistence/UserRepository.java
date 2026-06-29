package dev.alexandraemmeline.call_me_fluency.Infrastructure.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    void deleteByEmail(String email);

}
