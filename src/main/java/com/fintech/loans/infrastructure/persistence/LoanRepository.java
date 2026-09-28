package com.fintech.loans.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, String> {

    Optional<LoanEntity> findByLoanId(String loanId);

    List<LoanEntity> findByCustomerId(String customerId);

    @Query("SELECT l FROM LoanEntity l WHERE l.dueDate = :dueDate AND l.status = 'ACTIVE'")
    List<LoanEntity> findActiveLoansByDueDate(@Param("dueDate") LocalDate dueDate);

    @Query("SELECT l FROM LoanEntity l WHERE l.status = :status")
    List<LoanEntity> findByStatus(@Param("status") String status);

    @Query("SELECT l FROM LoanEntity l WHERE l.customerId = :customerId AND l.status = 'ACTIVE'")
    List<LoanEntity> findActiveLoansByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT COUNT(l) FROM LoanEntity l WHERE l.customerId = :customerId")
    long countByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT SUM(l.amount) FROM LoanEntity l WHERE l.customerId = :customerId AND l.status = 'ACTIVE'")
    java.math.BigDecimal sumActiveLoansAmountByCustomerId(@Param("customerId") String customerId);

    boolean existsByLoanId(String loanId);

    void deleteByLoanId(String loanId);
}