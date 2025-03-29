package com.example.project.dto.transaction;

public class RefundDto {
    private Long id;
    private String details;
    private Double amount;
    private String reason;
    private Boolean approved;

    // Constructors
    public RefundDto() {}

    public RefundDto(Long id, String details, Double amount, String reason, Boolean approved) {
        this.id = id;
        this.details = details;
        this.amount = amount;
        this.reason = reason;
        this.approved = approved;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Boolean getApproved() { return approved; }
    public void setApproved(Boolean approved) { this.approved = approved; }
}