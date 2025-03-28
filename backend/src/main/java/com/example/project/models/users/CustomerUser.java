package com.example.project.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

// Customer subclass
@Entity
@DiscriminatorValue("CUSTOMER")
public class CustomerUser extends User {
    @Column
    private String purchasedModels;

    public CustomerUser() {
        setRole("CUSTOMER");
    }

    @Override
    public String getRole() {
        return "CUSTOMER";
    }

    public void purchaseModel(String modelName) {
        if (this.purchasedModels == null) {
            this.purchasedModels = modelName;
        } else {
            this.purchasedModels += ", " + modelName;
        }
    }

    public String getPurchasedModels() { return purchasedModels; }
    public void setPurchasedModels(String purchasedModels) { this.purchasedModels = purchasedModels; }
}