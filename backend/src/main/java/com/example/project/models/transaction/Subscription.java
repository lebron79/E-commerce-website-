package com.example.project.models.transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Subscription extends Transaction {
    private String planName;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructors
    public Subscription() {
        super();
    }

    public Subscription(Long id, String details, Double amount, String planName, LocalDate startDate, LocalDate endDate) {
        super(id, details, amount);
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}