package com.example.project.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

// Admin subclass
@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {
    @Column
    private String adminLevel;

    public AdminUser() {
        setRole("ADMIN");
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

    // Override to always allow admin access
    @Override
    public boolean hasAdminAccess() {
        return true;
    }

    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }
}