package com.example.project.models.transaction;

import java.time.LocalDateTime;

public class Refund extends Transaction {
    private String reason;
    private Boolean approved;

    // Constructors
    public Refund() {
        super();
    }

    public Refund(Long id, String details, Double amount, String reason, Boolean approved) {
        super(id, details, amount);
        this.reason = reason;
        this.approved = approved;
    }

    // Getters and Setters
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Boolean getApproved() { return approved; }
    public void setApproved(Boolean approved) { this.approved = approved; }
}