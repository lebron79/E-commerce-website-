package com.example.project.repositories;

import com.example.project.dto.transaction.TransactionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDto, Long> {

    // Basic type filter
    List<TransactionDto> findByTransactionType(String transactionType);

    // Find by type and date range
    List<TransactionDto> findByTransactionTypeAndCreatedAtBetween(
            String transactionType,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    // Find by amount greater than
    List<TransactionDto> findByAmountGreaterThan(Double amount);

    // Find by approved status (for refunds)
    @Query("SELECT t FROM TransactionDto t WHERE t.transactionType = 'REFUND' AND t.approved = :approved")
    List<TransactionDto> findRefundsByApprovalStatus(Boolean approved);

    // Find active subscriptions (date range check)
    @Query("SELECT t FROM TransactionDto t WHERE t.transactionType = 'SUBSCRIPTION' " +
            "AND t.startDate <= CURRENT_DATE AND t.endDate >= CURRENT_DATE")
    List<TransactionDto> findActiveSubscriptions();

    // Count transactions by type
    @Query("SELECT t.transactionType, COUNT(t) FROM TransactionDto t GROUP BY t.transactionType")
    List<Object[]> countTransactionsByType();
}