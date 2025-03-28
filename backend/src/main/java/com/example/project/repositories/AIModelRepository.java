package com.example.project.repositories;

import com.example.project.models.aimodels.AIModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AIModelRepository extends JpaRepository<AIModel, Long> {
    // Find models by name
    Optional<AIModel> findByName(String name);

    // Find models by model type using the discriminator column
    @Query("SELECT m FROM AIModel m WHERE m.class = :modelType")
    List<AIModel> findByModelType(String modelType);

    // Check if a model exists by name
    boolean existsByName(String name);

    // Find models created after a certain timestamp
    List<AIModel> findByCreatedAtAfter(java.time.LocalDateTime timestamp);
}