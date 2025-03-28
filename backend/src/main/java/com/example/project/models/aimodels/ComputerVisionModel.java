package com.example.project.models.aimodels;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMPUTER_VISION")
public class ComputerVisionModel extends AIModel {
    @Column(name = "input_image_size")
    private String inputImageSize;

    @Column(name = "detection_accuracy")
    private Double detectionAccuracy;

    @Column(name = "pretrained_dataset")
    private String pretrainedDataset;

    @Column(name = "supported_tasks", length = 500)
    private String supportedTasks;

    @Column(name = "channel_support")
    private String channelSupport;

    // Default constructor
    public ComputerVisionModel() {
        super();
    }

    // Parameterized constructor
    public ComputerVisionModel(String name, String version, String description,
                               String inputImageSize, String pretrainedDataset) {
        super(name, version, description);
        this.inputImageSize = inputImageSize;
        this.pretrainedDataset = pretrainedDataset;
    }

    @Override
    public String getModelType() {
        return "COMPUTER_VISION";
    }

    // Getters and Setters
    public String getInputImageSize() { return inputImageSize; }
    public void setInputImageSize(String inputImageSize) { this.inputImageSize = inputImageSize; }

    public Double getDetectionAccuracy() { return detectionAccuracy; }
    public void setDetectionAccuracy(Double detectionAccuracy) { this.detectionAccuracy = detectionAccuracy; }

    public String getPretrainedDataset() { return pretrainedDataset; }
    public void setPretrainedDataset(String pretrainedDataset) { this.pretrainedDataset = pretrainedDataset; }

    public String getSupportedTasks() { return supportedTasks; }
    public void setSupportedTasks(String supportedTasks) { this.supportedTasks = supportedTasks; }

    public String getChannelSupport() { return channelSupport; }
    public void setChannelSupport(String channelSupport) { this.channelSupport = channelSupport; }
}