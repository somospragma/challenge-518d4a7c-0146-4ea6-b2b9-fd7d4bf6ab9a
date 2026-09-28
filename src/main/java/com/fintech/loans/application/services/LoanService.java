package com.fintech.loans.application.services;

import com.fintech.loans.application.ports.in.LoanServicePort;
import com.fintech.loans.application.ports.out.LoanPersistencePort;
import com.fintech.loans.domain.exception.ConnectionErrorException;
import com.fintech.loans.domain.exception.DatabaseUnavailableException;
import com.fintech.loans.domain.exception.InvalidLoanRequestException;
import com.fintech.loans.domain.model.Loan;
import com.fintech.loans.domain.model.LoanStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class LoanService implements LoanServicePort {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("1000.00");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("500000.00");
    private static final BigDecimal MIN_INTEREST_RATE = new BigDecimal("0.01");
    private static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("0.50");
    private static final int MIN_TERM_MONTHS = 6;
    private static final int MAX_TERM_MONTHS = 360;

    private final LoanPersistencePort persistencePort;

    public LoanService(LoanPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Loan createLoan(String customerId, BigDecimal amount, BigDecimal interestRate, int termMeses) {
        logger.info("Creating loan for customer: {}, amount: {}, rate: {}, term: {}",
                customerId, amount, interestRate, termMeses);

        validateLoanRequest(customerId, amount, interestRate, termMeses);

        Loan loan = new Loan();
        loan.setCustomerId(customerId);
        loan.setAmount(amount);
        loan.setInterestRate(interestRate);
        loan.setTermMonths(termMeses);
        loan.setStatus(LoanStatus.PENDING.name());
        loan.setStartDate(LocalDate.now());
        loan.generateId();

        BigDecimal monthlyPayment = calculateMonthlyPaymentAmount(amount, interestRate, termMeses);
        loan.setMonthlyPayment(monthlyPayment);

        LocalDate dueDate = loan.calculateDueDate();
        loan.setDueDate(dueDate);

        try {
            loan.validate();
            Loan savedLoan = persistencePort.save(loan);
            logger.info("Loan created successfully with ID: {}", savedLoan.getLoanId());
            return savedLoan;
        } catch (Exception e) {
            logger.error("Failed to create loan for customer: {}", customerId, e);
            if (e.getMessage() != null && e.getMessage().contains("database")) {
                throw new DatabaseUnavailableException("Database is currently unavailable", e);
            }
            throw new ConnectionErrorException("Failed to save loan to database", "LoanPersistence", 5000L, e);
        }
    }

    @Override
    public Optional<Loan> getLoanById(String loanId) {
        logger.debug("Retrieving loan by ID: {}", loanId);

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Loan ID cannot be null or empty");
        }

        try {
            Optional<Loan> loan = persistencePort.findById(loanId);
            if (loan.isEmpty()) {
                logger.warn("Loan not found with ID: {}", loanId);
            }
            return loan;
        } catch (Exception e) {
            logger.error("Error retrieving loan by ID: {}", loanId, e);
            throw new ConnectionErrorException("Failed to retrieve loan", "LoanPersistence", 3000L, e);
        }
    }

    @Override
    public List<Loan> getLoansByCustomer(String customerId) {
        logger.debug("Retrieving loans for customer: {}", customerId);

        if (customerId == null || customerId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Customer ID cannot be null or empty");
        }

        try {
            List<Loan> loans = persistencePort.findByCustomerId(customerId);
            logger.info("Found {} loans for customer: {}", loans.size(), customerId);
            return loans;
        } catch (Exception e) {
            logger.error("Error retrieving loans for customer: {}", customerId, e);
            throw new ConnectionErrorException("Failed to retrieve customer loans", "LoanPersistence", 3000L, e);
        }
    }

    @Override
    public Loan updateLoanStatus(String loanId, String newStatus) {
        logger.info("Updating loan status - ID: {}, new status: {}", loanId, newStatus);

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Loan ID cannot be null or empty");
        }

        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Status cannot be null or empty");
        }

        try {
            Loan existingLoan = persistencePort.findById(loanId)
                    .orElseThrow(() -> new InvalidLoanRequestException("Loan not found with ID: " + loanId));

            LoanStatus statusEnum = LoanStatus.fromString(newStatus);
            if (statusEnum == null) {
                throw new InvalidLoanRequestException("Invalid loan status: " + newStatus);
            }

            Loan updatedLoan = persistencePort.updateStatus(loanId, newStatus);
            logger.info("Loan status updated successfully - ID: {}, status: {}", loanId, newStatus);
            return updatedLoan;
        } catch (InvalidLoanRequestException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error updating loan status - ID: {}", loanId, e);
            throw new ConnectionErrorException("Failed to update loan status", "LoanPersistence", 5000L, e);
        }
    }

    @Override
    public BigDecimal calculateMonthlyPayment(String loanId) {
        logger.debug("Calculating monthly payment for loan: {}", loanId);

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Loan ID cannot be null or empty");
        }

        try {
            Loan loan = persistencePort.findById(loanId)
                    .orElseThrow(() -> new InvalidLoanRequestException("Loan not found with ID: " + loanId));

            BigDecimal monthlyPayment = loan.calculateMonthlyPayment();
            logger.debug("Monthly payment calculated: {} for loan: {}", monthlyPayment, loanId);
            return monthlyPayment;
        } catch (InvalidLoanRequestException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error calculating monthly payment for loan: {}", loanId, e);
            throw new ConnectionErrorException("Failed to calculate monthly payment", "LoanPersistence", 3000L, e);
        }
    }

    @Override
    public List<Loan> getLoansDueOn(LocalDate dueDate) {
        logger.debug("Retrieving loans due on: {}", dueDate);

        if (dueDate == null) {
            throw new InvalidLoanRequestException("Due date cannot be null");
        }

        try {
            List<Loan> loans = persistencePort.findByDueDate(dueDate);
            logger.info("Found {} loans due on: {}", loans.size(), dueDate);
            return loans;
        } catch (Exception e) {
            logger.error("Error retrieving loans due on: {}", dueDate, e);
            throw new ConnectionErrorException("Failed to retrieve loans by due date", "LoanPersistence", 3000L, e);
        }
    }

    private void validateLoanRequest(String customerId, BigDecimal amount, BigDecimal interestRate, int termMeses) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Customer ID cannot be null or empty");
        }

        if (amount == null) {
            throw new InvalidLoanRequestException("Loan amount cannot be null");
        }

        if (amount.compareTo(MIN_AMOUNT) < 0) {
            throw new InvalidLoanRequestException(
                    String.format("Loan amount must be at least %s", MIN_AMOUNT));
        }

        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new InvalidLoanRequestException(
                    String.format("Loan amount cannot exceed %s", MAX_AMOUNT));
        }

        if (interestRate == null) {
            throw new InvalidLoanRequestException("Interest rate cannot be null");
        }

        if (interestRate.compareTo(MIN_INTEREST_RATE) < 0) {
            throw new InvalidLoanRequestException(
                    String.format("Interest rate must be at least %s%%", MIN_INTEREST_RATE.multiply(BigDecimal.valueOf(100))));
        }

        if (interestRate.compareTo(MAX_INTEREST_RATE) > 0) {
            throw new InvalidLoanRequestException(
                    String.format("Interest rate cannot exceed %s%%", MAX_INTEREST_RATE.multiply(BigDecimal.valueOf(100))));
        }

        if (termMeses < MIN_TERM_MONTHS) {
            throw new InvalidLoanRequestException(
                    String.format("Loan term must be at least %d months", MIN_TERM_MONTHS));
        }

        if (termMeses > MAX_TERM_MONTHS) {
            throw new InvalidLoanRequestException(
                    String.format("Loan term cannot exceed %d months", MAX_TERM_MONTHS));
        }
    }

    private BigDecimal calculateMonthlyPaymentAmount(BigDecimal principal, BigDecimal annualRate, int termMonths) {
        if (principal == null || annualRate == null || termMonths <= 0) {
            throw new InvalidLoanRequestException("Invalid parameters for payment calculation");
        }

        if (annualRate.compareTo(BigDecimal.ZERO) == 0) {
            return principal.divide(BigDecimal.valueOf(termMonths), 2, RoundingMode.HALF_UP);
        }

        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), 6, RoundingMode.HALF_UP);
        BigDecimal onePlusRatePowN = BigDecimal.ONE.add(monthlyRate).pow(termMonths);
        BigDecimal monthlyPayment = principal.multiply(monthlyRate).multiply(onePlusRatePowN)
                .divide(onePlusRatePowN.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);

        return monthlyPayment;
    }
}