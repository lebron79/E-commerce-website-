package com.example.project.controllers;

import com.example.project.dto.aimodel.AIModelRegistrationRequest;
import com.example.project.models.aimodels.AIModel;
import com.example.project.services.AIModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class AIModelController {

    private final AIModelService aiModelService;

    @Autowired
    public AIModelController(AIModelService aiModelService) {
        this.aiModelService = aiModelService;
    }

    @PostMapping("/register")
    public ResponseEntity<AIModel> registerModel(@RequestBody AIModelRegistrationRequest registrationRequest) {
        AIModel registeredModel = aiModelService.registerModel(registrationRequest);
        return new ResponseEntity<>(registeredModel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AIModel> getModelById(@PathVariable Long id) {
        AIModel model = aiModelService.getModelById(id);
        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<List<AIModel>> getAllModels() {
        List<AIModel> models = aiModelService.getAllModels();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/type/{modelType}")
    public ResponseEntity<List<AIModel>> getModelsByType(@PathVariable String modelType) {
        List<AIModel> models = aiModelService.getModelsByType(modelType);
        return ResponseEntity.ok(models);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AIModel> updateModel(
            @PathVariable Long id,
            @RequestBody AIModelRegistrationRequest updateRequest) {
        AIModel updatedModel = aiModelService.updateModel(id, updateRequest);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        aiModelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}