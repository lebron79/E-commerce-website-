package com.example.project.models.users;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Add an explicit column for role
    @Column(name = "role", nullable = false)
    private String role;

    // Add discriminator column
    @Column(name = "user_type", insertable = false, updatable = false)
    private String userType;

    // Abstract method for polymorphism
    public abstract String getRole();

    // Override the getRole method to set the role
    public void setRole(String role) {
        this.role = role;
    }

    // Common method demonstrating polymorphic behavior
    public boolean hasAdminAccess() {
        return false;
    }

    // Constructors
    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUserType() { return userType; }
}