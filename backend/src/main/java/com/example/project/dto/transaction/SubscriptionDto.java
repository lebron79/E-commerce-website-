package com.example.project.dto.transaction;

import java.time.LocalDate;

public class SubscriptionDto {
    private Long id;
    private String details;
    private Double amount;
    private String planName;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructors
    public SubscriptionDto() {}

    public SubscriptionDto(Long id, String details, Double amount, String planName, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.details = details;
        this.amount = amount;
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}