package dev.alexandraemmeline.call_me_fluency.Core.Domains;

import dev.alexandraemmeline.call_me_fluency.Core.Enums.Level;
import dev.alexandraemmeline.call_me_fluency.Core.Enums.Status;
import dev.alexandraemmeline.call_me_fluency.Core.Exceptions.DomainException;

import java.time.LocalDateTime;

public class UserDomain {

    private Long id;
    private String name;
    private String email;
    private String passwordHash;
    private LocalDateTime createdAt;
    private Level level;
    private Status status;


    public UserDomain(Long id, String name, String email, String passwordHash, LocalDateTime createdAt, Level level) {

        validateName(name);
        validateEmail(email);
        validatePassword(passwordHash);

        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.level = level;
        statusActivate();
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

    private void validatePassword(String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new DomainException("Password is required.");
        }

        if (passwordHash.length() < 8) {
            throw new DomainException("The password must have at least 8 characters");
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

    public Level getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }


    public void changeName(String newName) {
        validateName(newName);
        this.name = newName;

    }

    public void changePasswordHash(String newPasswordHash) {
        validatePassword(newPasswordHash);
        this.passwordHash = newPasswordHash;

    }

    public void statusActivate() {
        this.status = Status.ACTIVE;
    }

    public void statusDisabled() {
        this.status = Status.INACTIVE;
    }

    public void promoteTo(Level level) {
        this.level = level;
    }


}

