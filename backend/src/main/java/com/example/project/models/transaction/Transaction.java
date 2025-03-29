package com.example.project.models.transaction;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private Long id;
    private String details;
    private Double amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Transaction() {
        this.createdAt = LocalDateTime.now();
    }

    public Transaction(Long id, String details, Double amount) {
        this.id = id;
        this.details = details;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}