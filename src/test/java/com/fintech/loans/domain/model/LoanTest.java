package com.fintech.loans.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;

@DisplayName("Loan - Tests de la clase de dominio")
class LoanTest {

    @Test
    @DisplayName("Debería crear un préstamo con los valores correctos")
    void shouldCreateLoanWithCorrectValues() {
        // Given
        String customerId = "CUST-001";
        BigDecimal amount = new BigDecimal("10000.00");
        BigDecimal interestRate = new BigDecimal("0.05");
        int termMonths = 12;

        // When
        Loan loan = new Loan();
        loan.setCustomerId(customerId);
        loan.setAmount(amount);
        loan.setInterestRate(interestRate);
        loan.setTermMonths(termMonths);
        loan.setStatus("PENDING");
        loan.generateId();

        // Then
        Assertions.assertNotNull(loan.getLoanId());
        Assertions.assertEquals(customerId, loan.getCustomerId());
        Assertions.assertEquals(amount, loan.getAmount());
    }

    @Test
    @DisplayName("Debería calcular la cuota mensual correctamente")
    void shouldCalculateMonthlyPaymentCorrectly() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("12000.00"));
        loan.setInterestRate(new BigDecimal("0.10"));
        loan.setTermMonths(12);

        // When
        BigDecimal monthlyPayment = loan.calculateMonthlyPayment();

        // Then
        Assertions.assertNotNull(monthlyPayment);
        Assertions.assertTrue(monthlyPayment.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName("Debería calcular la fecha de vencimiento correctamente")
    void shouldCalculateDueDateCorrectly() {
        // Given
        Loan loan = new Loan();
        loan.setStartDate(LocalDate.of(2024, 1, 15));
        loan.setTermMonths(6);

        // When
        LocalDate dueDate = loan.calculateDueDate();

        // Then
        Assertions.assertNotNull(dueDate);
        Assertions.assertEquals(2024, dueDate.getYear());
        Assertions.assertEquals(7, dueDate.getMonthValue());
    }

    @Test
    @DisplayName("Debería validar un préstamo correctamente")
    void shouldValidateLoanCorrectly() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("5000.00"));
        loan.setInterestRate(new BigDecimal("0.03"));
        loan.setTermMonths(24);

        // When
        loan.validate();

        // Then
        Assertions.assertDoesNotThrow(() -> loan.validate());
    }

    @Test
    @DisplayName("Debería rechazar monto negativo o cero")
    void shouldRejectNegativeOrZeroAmount() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(BigDecimal.ZERO);
        loan.setInterestRate(new BigDecimal("0.05"));
        loan.setTermMonths(12);

        // When & Then
        Assertions.assertThrows(Exception.class, () -> loan.validate());
    }

    @Test
    @DisplayName("Debería rechazar tasa de interés negativa")
    void shouldRejectNegativeInterestRate() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("-0.05"));
        loan.setTermMonths(12);

        // When & Then
        Assertions.assertThrows(Exception.class, () -> loan.validate());
    }

    @Test
    @DisplayName("Debería rechazar plazo menor a 1 mes")
    void shouldRejectTermLessThanOneMonth() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.05"));
        loan.setTermMonths(0);

        // When & Then
        Assertions.assertThrows(Exception.class, () -> loan.validate());
    }
}