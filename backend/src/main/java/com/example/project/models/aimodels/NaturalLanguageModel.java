package com.example.project.models.aimodels;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NATURAL_LANGUAGE")
public class NaturalLanguageModel extends AIModel {
    @Column(name = "language")
    private String language;

    @Column(name = "model_architecture")
    private String modelArchitecture;

    @Column(name = "context_window_size")
    private Integer contextWindowSize;

    @Column(name = "token_count")
    private Long tokenCount;

    @Column(name = "supported_tasks", length = 500)
    private String supportedTasks;

    // Default constructor
    public NaturalLanguageModel() {
        super();
    }

    // Parameterized constructor
    public NaturalLanguageModel(String name, String version, String description,
                                String language, String modelArchitecture) {
        super(name, version, description);
        this.language = language;
        this.modelArchitecture = modelArchitecture;
    }

    @Override
    public String getModelType() {
        return "NATURAL_LANGUAGE";
    }

    // Getters and Setters
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getModelArchitecture() { return modelArchitecture; }
    public void setModelArchitecture(String modelArchitecture) { this.modelArchitecture = modelArchitecture; }

    public Integer getContextWindowSize() { return contextWindowSize; }
    public void setContextWindowSize(Integer contextWindowSize) { this.contextWindowSize = contextWindowSize; }

    public Long getTokenCount() { return tokenCount; }
    public void setTokenCount(Long tokenCount) { this.tokenCount = tokenCount; }

    public String getSupportedTasks() { return supportedTasks; }
    public void setSupportedTasks(String supportedTasks) { this.supportedTasks = supportedTasks; }
}