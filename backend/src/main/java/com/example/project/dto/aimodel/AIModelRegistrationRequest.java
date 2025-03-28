package com.example.project.dto.aimodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "modelType",
        visible = true  // Add this to make modelType visible during deserialization
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MachineLearningModelRequest.class, name = "MACHINE_LEARNING"),
        @JsonSubTypes.Type(value = NaturalLanguageModelRequest.class, name = "NATURAL_LANGUAGE"),
        @JsonSubTypes.Type(value = ComputerVisionModelRequest.class, name = "COMPUTER_VISION")
})
public abstract class AIModelRegistrationRequest {  // Make this abstract
    private String name;
    private String version;
    private String description;
    private String modelType;  // This will be populated from the JSON

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getModelType() { return modelType; }
    public void setModelType(String modelType) { this.modelType = modelType; }
}