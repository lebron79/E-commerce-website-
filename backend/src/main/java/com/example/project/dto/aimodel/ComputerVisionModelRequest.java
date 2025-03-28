package com.example.project.dto.aimodel;

// DTO for Computer Vision Model specific details
public class ComputerVisionModelRequest extends AIModelRegistrationRequest {
    private String inputImageSize;
    private Double detectionAccuracy;
    private String pretrainedDataset;
    private String supportedTasks;
    private String channelSupport;

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
