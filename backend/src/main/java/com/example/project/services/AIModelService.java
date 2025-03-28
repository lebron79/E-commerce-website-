package com.example.project.services;

import com.example.project.dto.aimodel.AIModelRegistrationRequest;
import com.example.project.dto.aimodel.ComputerVisionModelRequest;
import com.example.project.dto.aimodel.MachineLearningModelRequest;
import com.example.project.dto.aimodel.NaturalLanguageModelRequest;
import com.example.project.models.aimodels.AIModel;
import com.example.project.models.aimodels.ComputerVisionModel;
import com.example.project.models.aimodels.MachineLearningModel;
import com.example.project.models.aimodels.NaturalLanguageModel;
import com.example.project.repositories.AIModelRepository;
import com.example.project.exceptions.ModelNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AIModelService {
    private static final Logger logger = LoggerFactory.getLogger(AIModelService.class);

    private final AIModelRepository aiModelRepository;

    @Autowired
    public AIModelService(AIModelRepository aiModelRepository) {
        this.aiModelRepository = aiModelRepository;
    }

    @Transactional
    public AIModel registerModel(AIModelRegistrationRequest registrationRequest) {
        try {
            // Check for duplicate names
            if (aiModelRepository.existsByName(registrationRequest.getName())) {
                throw new IllegalArgumentException("Model with name " + registrationRequest.getName() + " already exists");
            }

            AIModel model;
            String modelType = registrationRequest.getModelType().toUpperCase();

            switch (modelType) {
                case "MACHINE_LEARNING":
                    if (!(registrationRequest instanceof MachineLearningModelRequest)) {
                        throw new IllegalArgumentException("Invalid request type for MACHINE_LEARNING model");
                    }
                    MachineLearningModelRequest mlRequest = (MachineLearningModelRequest) registrationRequest;
                    MachineLearningModel mlModel = new MachineLearningModel(
                            mlRequest.getName(),
                            mlRequest.getVersion(),
                            mlRequest.getDescription(),
                            mlRequest.getAlgorithmType(),
                            mlRequest.getTrainingDatasetSize()
                    );
                    mlModel.setAccuracyScore(mlRequest.getAccuracyScore());
                    mlModel.setHyperparameters(mlRequest.getHyperparameters());
                    mlModel.setTrainingDate(mlRequest.getTrainingDate());
                    model = mlModel;
                    break;

                case "NATURAL_LANGUAGE":
                    if (!(registrationRequest instanceof NaturalLanguageModelRequest)) {
                        throw new IllegalArgumentException("Invalid request type for NATURAL_LANGUAGE model");
                    }
                    NaturalLanguageModelRequest nlRequest = (NaturalLanguageModelRequest) registrationRequest;
                    NaturalLanguageModel nlModel = new NaturalLanguageModel(
                            nlRequest.getName(),
                            nlRequest.getVersion(),
                            nlRequest.getDescription(),
                            nlRequest.getLanguage(),
                            nlRequest.getModelArchitecture()
                    );
                    nlModel.setContextWindowSize(nlRequest.getContextWindowSize());
                    nlModel.setTokenCount(nlRequest.getTokenCount());
                    nlModel.setSupportedTasks(nlRequest.getSupportedTasks());
                    model = nlModel;
                    break;

                case "COMPUTER_VISION":
                    if (!(registrationRequest instanceof ComputerVisionModelRequest)) {
                        throw new IllegalArgumentException("Invalid request type for COMPUTER_VISION model");
                    }
                    ComputerVisionModelRequest cvRequest = (ComputerVisionModelRequest) registrationRequest;
                    ComputerVisionModel cvModel = new ComputerVisionModel(
                            cvRequest.getName(),
                            cvRequest.getVersion(),
                            cvRequest.getDescription(),
                            cvRequest.getInputImageSize(),
                            cvRequest.getPretrainedDataset()
                    );
                    cvModel.setDetectionAccuracy(cvRequest.getDetectionAccuracy());
                    cvModel.setSupportedTasks(cvRequest.getSupportedTasks());
                    cvModel.setChannelSupport(cvRequest.getChannelSupport());
                    model = cvModel;
                    break;

                default:
                    throw new IllegalArgumentException("Invalid model type: " + modelType);
            }

            AIModel savedModel = aiModelRepository.save(model);
            logger.info("Model registered successfully - ID: {}, Name: {}", savedModel.getId(), savedModel.getName());
            return savedModel;
        } catch (Exception e) {
            logger.error("Error registering model", e);
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public AIModel getModelById(Long id) {
        return aiModelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Model not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<AIModel> getAllModels() {
        return aiModelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AIModel> getModelsByType(String modelType) {
        return aiModelRepository.findByModelType(modelType);
    }

    @Transactional
    public AIModel updateModel(Long id, AIModelRegistrationRequest updateRequest) {
        AIModel existingModel = getModelById(id);

        // Update common fields
        existingModel.setName(updateRequest.getName());
        existingModel.setVersion(updateRequest.getVersion());
        existingModel.setDescription(updateRequest.getDescription());

        // Type-specific updates
        if (existingModel instanceof MachineLearningModel mlModel &&
                updateRequest instanceof MachineLearningModelRequest mlRequest) {
            mlModel.setAlgorithmType(mlRequest.getAlgorithmType());
            mlModel.setTrainingDatasetSize(mlRequest.getTrainingDatasetSize());
            mlModel.setAccuracyScore(mlRequest.getAccuracyScore());
        } else if (existingModel instanceof NaturalLanguageModel nlModel &&
                updateRequest instanceof NaturalLanguageModelRequest nlRequest) {
            nlModel.setLanguage(nlRequest.getLanguage());
            nlModel.setModelArchitecture(nlRequest.getModelArchitecture());
            nlModel.setContextWindowSize(nlRequest.getContextWindowSize());
        } else if (existingModel instanceof ComputerVisionModel cvModel &&
                updateRequest instanceof ComputerVisionModelRequest cvRequest) {
            cvModel.setInputImageSize(cvRequest.getInputImageSize());
            cvModel.setPretrainedDataset(cvRequest.getPretrainedDataset());
            cvModel.setDetectionAccuracy(cvRequest.getDetectionAccuracy());
        }

        return aiModelRepository.save(existingModel);
    }

    @Transactional
    public void deleteModel(Long id) {
        AIModel model = getModelById(id);
        aiModelRepository.delete(model);
        logger.info("Model deleted successfully - ID: {}", id);
    }
}