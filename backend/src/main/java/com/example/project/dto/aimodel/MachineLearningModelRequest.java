package com.example.project.dto.aimodel;

import java.time.LocalDate;

// DTO for Machine Learning Model specific details
public class MachineLearningModelRequest extends AIModelRegistrationRequest {
    private String algorithmType;
    private Long trainingDatasetSize;
    private Double accuracyScore;
    private String hyperparameters;
    private LocalDate trainingDate;

    // Getters and Setters
    public String getAlgorithmType() { return algorithmType; }
    public void setAlgorithmType(String algorithmType) { this.algorithmType = algorithmType; }

    public Long getTrainingDatasetSize() { return trainingDatasetSize; }
    public void setTrainingDatasetSize(Long trainingDatasetSize) { this.trainingDatasetSize = trainingDatasetSize; }

    public Double getAccuracyScore() { return accuracyScore; }
    public void setAccuracyScore(Double accuracyScore) { this.accuracyScore = accuracyScore; }

    public String getHyperparameters() { return hyperparameters; }
    public void setHyperparameters(String hyperparameters) { this.hyperparameters = hyperparameters; }

    public LocalDate getTrainingDate() { return trainingDate; }
    public void setTrainingDate(LocalDate trainingDate) { this.trainingDate = trainingDate; }
}
