package com.example.project.dto.aimodel;

// DTO for Natural Language Model specific details
public class NaturalLanguageModelRequest extends AIModelRegistrationRequest {
    private String language;
    private String modelArchitecture;
    private Integer contextWindowSize;
    private Long tokenCount;
    private String supportedTasks;

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
