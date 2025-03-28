package com.example.project.dto.user;

public class UserUpdateRequest {
    private String username;
    private String email;
    private String password;

    // Default constructor
    public UserUpdateRequest() {}

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Optional: toString method for logging
    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                "}";
    }
}