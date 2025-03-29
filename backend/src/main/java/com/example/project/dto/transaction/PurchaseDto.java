package com.example.project.dto.transaction;

public class PurchaseDto {
    private Long id;
    private String details;
    private Double amount;
    private String productName;
    private Integer quantity;

    // Constructors
    public PurchaseDto() {}

    public PurchaseDto(Long id, String details, Double amount, String productName, Integer quantity) {
        this.id = id;
        this.details = details;
        this.amount = amount;
        this.productName = productName;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}