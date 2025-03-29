package com.example.project.services.transaction;

import com.example.project.dto.transaction.*;
import com.example.project.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    // Generic Transaction Methods
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        return transactionRepository.save(transactionDto);
    }

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public TransactionDto getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public TransactionDto updateTransaction(Long id, TransactionDto transactionDto) {
        TransactionDto existingTransaction = getTransactionById(id);
        existingTransaction.setDetails(transactionDto.getDetails());
        existingTransaction.setAmount(transactionDto.getAmount());
        return transactionRepository.save(existingTransaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // New method to get transactions by type
    public List<TransactionDto> getTransactionsByType(String transactionType) {
        return transactionRepository.findByTransactionType(transactionType);
    }

    // Specific Transaction Type Methods
    public PurchaseDto createPurchase(PurchaseDto purchaseDto) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setDetails(purchaseDto.getDetails());
        transactionDto.setAmount(purchaseDto.getAmount());
        transactionDto.setTransactionType("PURCHASE");
        transactionDto.setProductName(purchaseDto.getProductName());
        transactionDto.setQuantity(purchaseDto.getQuantity());

        TransactionDto saved = transactionRepository.save(transactionDto);
        return convertToPurchaseDto(saved);
    }

    public RefundDto createRefund(RefundDto refundDto) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setDetails(refundDto.getDetails());
        transactionDto.setAmount(refundDto.getAmount());
        transactionDto.setTransactionType("REFUND");
        transactionDto.setReason(refundDto.getReason());
        transactionDto.setApproved(refundDto.getApproved());

        TransactionDto saved = transactionRepository.save(transactionDto);
        return convertToRefundDto(saved);
    }

    public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setDetails(subscriptionDto.getDetails());
        transactionDto.setAmount(subscriptionDto.getAmount());
        transactionDto.setTransactionType("SUBSCRIPTION");
        transactionDto.setPlanName(subscriptionDto.getPlanName());
        transactionDto.setStartDate(subscriptionDto.getStartDate());
        transactionDto.setEndDate(subscriptionDto.getEndDate());

        TransactionDto saved = transactionRepository.save(transactionDto);
        return convertToSubscriptionDto(saved);
    }

    // Conversion methods
    private PurchaseDto convertToPurchaseDto(TransactionDto transaction) {
        return new PurchaseDto(
                transaction.getId(),
                transaction.getDetails(),
                transaction.getAmount(),
                transaction.getProductName(),
                transaction.getQuantity()
        );
    }

    private RefundDto convertToRefundDto(TransactionDto transaction) {
        return new RefundDto(
                transaction.getId(),
                transaction.getDetails(),
                transaction.getAmount(),
                transaction.getReason(),
                transaction.getApproved()
        );
    }

    private SubscriptionDto convertToSubscriptionDto(TransactionDto transaction) {
        return new SubscriptionDto(
                transaction.getId(),
                transaction.getDetails(),
                transaction.getAmount(),
                transaction.getPlanName(),
                transaction.getStartDate(),
                transaction.getEndDate()
        );
    }
}