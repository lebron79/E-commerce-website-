package com.example.project.models.transaction;

import java.time.LocalDateTime;

public class Purchase extends Transaction {
    private String productName;
    private Integer quantity;

    // Constructors
    public Purchase() {
        super();
    }

    public Purchase(Long id, String details, Double amount, String productName, Integer quantity) {
        super(id, details, amount);
        this.productName = productName;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}