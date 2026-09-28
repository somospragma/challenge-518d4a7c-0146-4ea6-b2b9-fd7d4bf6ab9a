package com.fintech.loans.infrastructure.persistence;

import com.fintech.loans.domain.model.Loan;
import com.fintech.loans.domain.model.LoanStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class LoanEntity {

    @Id
    @Column(name = "loan_id", length = 36)
    private String loanId;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "term_months", nullable = false)
    private int termMonths;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "monthly_payment", nullable = false, precision = 19, scale = 4)
    private BigDecimal monthlyPayment;

    public LoanEntity() {
    }

    public LoanEntity(String loanId, String customerId, BigDecimal amount, BigDecimal interestRate,
                      int termMonths, LoanStatus status, LocalDate startDate, LocalDate dueDate,
                      BigDecimal monthlyPayment) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.monthlyPayment = monthlyPayment;
    }

    public static LoanEntity fromDomain(Loan loan) {
        LoanEntity entity = new LoanEntity();
        entity.loanId = loan.getLoanId();
        entity.customerId = loan.getCustomerId();
        entity.amount = loan.getAmount();
        entity.interestRate = loan.getInterestRate();
        entity.termMonths = loan.getTermMonths();
        entity.status = LoanStatus.fromString(loan.getStatus());
        entity.startDate = loan.getStartDate();
        entity.dueDate = loan.getDueDate();
        entity.monthlyPayment = loan.getMonthlyPayment();
        return entity;
    }

    public Loan toDomain() {
        Loan loan = new Loan();
        loan.setLoanId(this.loanId);
        loan.setCustomerId(this.customerId);
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setTermMonths(this.termMonths);
        loan.setStatus(this.status.name());
        loan.setStartDate(this.startDate);
        loan.setDueDate(this.dueDate);
        loan.setMonthlyPayment(this.monthlyPayment);
        return loan;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}