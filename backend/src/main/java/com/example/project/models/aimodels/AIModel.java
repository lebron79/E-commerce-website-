package com.example.project.models.aimodels;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_models")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "model_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AIModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String version;

    @Column(length = 1000)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    // Add the modelType column that corresponds to the discriminator
    @Column(name = "model_type", insertable = false, updatable = false)
    private String modelType;

    // Abstract method for polymorphic behavior
    public abstract String getModelType();

    // Constructors
    public AIModel() {
        this.createdAt = LocalDateTime.now();
    }

    public AIModel(String name, String version, String description) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }

    // New getter for the discriminator column
    public String getModelTypeColumn() { return modelType; }

    @PreUpdate
    public void updateLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }
}