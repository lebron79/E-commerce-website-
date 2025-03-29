package com.example.project.controllers.transaction;

import com.example.project.dto.transaction.*;
import com.example.project.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    // Generic Transaction Endpoints
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionDto));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(
            @PathVariable Long id,
            @RequestBody TransactionDto transactionDto
    ) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, transactionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    // Specific Transaction Type Endpoints
    @PostMapping("/purchase")
    public ResponseEntity<PurchaseDto> createPurchase(@RequestBody PurchaseDto purchaseDto) {
        return ResponseEntity.ok(transactionService.createPurchase(purchaseDto));
    }

    @PostMapping("/refund")
    public ResponseEntity<RefundDto> createRefund(@RequestBody RefundDto refundDto) {
        return ResponseEntity.ok(transactionService.createRefund(refundDto));
    }

    @PostMapping("/subscription")
    public ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        return ResponseEntity.ok(transactionService.createSubscription(subscriptionDto));
    }

    // Add these endpoints to fetch by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByType(@PathVariable String type) {
        return ResponseEntity.ok(transactionService.getTransactionsByType(type));
    }
}