package dev.alexandraemmeline.call_me_fluency.Core.Domains;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.RoleName;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.UserLevel;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.UserStatus;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.DomainException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserDomain {

    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private LocalDateTime createdAt;
    private UserLevel userLevel;
    private UserStatus userStatus;
    private final Set<RoleName> roles = new HashSet<>();


    public UserDomain(Long id, String name, String email, String passwordHash, LocalDateTime createdAt, UserLevel userLevel, UserStatus userStatus) {

        validateName(name);
        validateEmail(email);
        validatePasswordHash(passwordHash);

        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.userLevel = userLevel;
        this.userStatus = userStatus;
    }


    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new DomainException("Name is required.");
        }

        if (name.length() < 3) {
            throw new DomainException("The name must have at least 3 characters.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new DomainException("Email is required.");
        }

        if (!email.contains("@")) {
            throw new DomainException("Invalid email.");
        }
    }

    private void validatePasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new DomainException("Password hash is required.");
        }
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public Set<RoleName> getRoles() {
        return Set.copyOf(roles);
    }



    public void changeName(String newName) {
        validateName(newName);
        this.name = newName;

    }

    public void changePasswordHash(String newPasswordHash) {
        validatePasswordHash(newPasswordHash);
        this.passwordHash = newPasswordHash;

    }

    public void statusActivate() {
        this.userStatus = UserStatus.ACTIVE;
    }

    public void statusDisabled() {
        this.userStatus = UserStatus.INACTIVE;
    }

    public void markAsCreated() {

        if (this.createdAt != null) {
            return;
        }

        this.createdAt = LocalDateTime.now();
    }

    public void promoteTo(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public void addRole (RoleName roleName) {
        if (roleName == null) {
            throw new DomainException("Role cannot be null.");
        }

        roles.add(roleName);
    }


}

