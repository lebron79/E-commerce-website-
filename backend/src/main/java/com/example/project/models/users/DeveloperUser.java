package com.example.project.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

// Developer subclass
@Entity
@DiscriminatorValue("DEVELOPER")
public class DeveloperUser extends User {
    @Column
    private String specialization;

    @Column
    private String publishedModels;

    public DeveloperUser() {
        setRole("DEVELOPER");
    }

    @Override
    public String getRole() {
        return "DEVELOPER";
    }

    public void publishModel(String modelName) {
        if (this.publishedModels == null) {
            this.publishedModels = modelName;
        } else {
            this.publishedModels += ", " + modelName;
        }
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getPublishedModels() { return publishedModels; }
    public void setPublishedModels(String publishedModels) { this.publishedModels = publishedModels; }
}