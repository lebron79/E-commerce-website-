package com.example.project.models.aimodels;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("MACHINE_LEARNING")
public class MachineLearningModel extends AIModel {
    @Column(name = "algorithm_type")
    private String algorithmType;

    @Column(name = "training_dataset_size")
    private Long trainingDatasetSize;

    @Column(name = "accuracy_score")
    private Double accuracyScore;

    @Column(name = "hyperparameters", length = 1000)
    private String hyperparameters;

    @Column(name = "training_date")
    private LocalDate trainingDate;

    // Default constructor
    public MachineLearningModel() {
        super();
    }

    // Parameterized constructor
    public MachineLearningModel(String name, String version, String description,
                                String algorithmType, Long trainingDatasetSize) {
        super(name, version, description);
        this.algorithmType = algorithmType;
        this.trainingDatasetSize = trainingDatasetSize;
        this.trainingDate = LocalDate.now();
    }

    @Override
    public String getModelType() {
        return "MACHINE_LEARNING";
    }

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