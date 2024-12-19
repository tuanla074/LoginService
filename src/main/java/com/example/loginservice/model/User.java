package com.example.loginservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    private String fullname;
    private String username;
    private String password;
    private String passwordSalt;

    @Column(columnDefinition = "boolean default false")
    private Boolean isLoggedIn;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
