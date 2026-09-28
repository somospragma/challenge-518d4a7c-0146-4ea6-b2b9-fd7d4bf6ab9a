package com.fintech.loans.application.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fintech.loans.application.ports.out.LoanPersistencePort;
import com.fintech.loans.domain.model.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("LoanService - Tests del servicio de préstamos")
@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanPersistencePort loanPersistencePort;

    @InjectMocks
    private LoanService loanService;

    @Test
    @DisplayName("Debería crear un préstamo exitosamente")
    void shouldCreateLoanSuccessfully() {
        // Given
        String customerId = "CUST-001";
        BigDecimal amount = new BigDecimal("15000.00");
        BigDecimal interestRate = new BigDecimal("0.08");
        int termMonths = 24;

        Loan savedLoan = new Loan();
        savedLoan.setLoanId("LOAN-001");
        savedLoan.setCustomerId(customerId);
        savedLoan.setAmount(amount);
        savedLoan.setInterestRate(interestRate);
        savedLoan.setTermMonths(termMonths);
        savedLoan.setStatus("PENDING");
        savedLoan.setStartDate(LocalDate.now());
        savedLoan.setMonthlyPayment(new BigDecimal("700.00"));

        when(loanPersistencePort.save(any(Loan.class))).thenReturn(savedLoan);

        // When
        Loan result = loanService.createLoan(customerId, amount, interestRate, termMonths);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(customerId, result.getCustomerId());
        verify(loanPersistencePort, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("Debería obtener un préstamo por ID")
    void shouldGetLoanById() {
        // Given
        String loanId = "LOAN-001";
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        loan.setCustomerId("CUST-001");
        loan.setAmount(new BigDecimal("10000.00"));

        when(loanPersistencePort.findById(loanId)).thenReturn(Optional.of(loan));

        // When
        Optional<Loan> result = loanService.getLoanById(loanId);

        // Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(loanId, result.get().getLoanId());
    }

    @Test
    @DisplayName("Debería obtener préstamos por cliente")
    void shouldGetLoansByCustomer() {
        // Given
        String customerId = "CUST-001";
        List<Loan> loans = Arrays.asList(createLoan("LOAN-001", customerId),
                                         createLoan("LOAN-002", customerId));

        when(loanPersistencePort.findByCustomerId(customerId)).thenReturn(loans);

        // When
        List<Loan> result = loanService.getLoansByCustomer(customerId);

        // Then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Debería actualizar el estado de un préstamo")
    void shouldUpdateLoanStatus() {
        // Given
        String loanId = "LOAN-001";
        String newStatus = "APPROVED";
        Loan loan = createLoan(loanId, "CUST-001");

        when(loanPersistencePort.findById(loanId)).thenReturn(Optional.of(loan));
        when(loanPersistencePort.updateStatus(loanId, newStatus)).thenReturn(loan);

        // When
        Loan result = loanService.updateLoanStatus(loanId, newStatus);

        // Then
        Assertions.assertNotNull(result);
        verify(loanPersistencePort, times(1)).updateStatus(loanId, newStatus);
    }

    @Test
    @DisplayName("Debería calcular la cuota mensual de un préstamo")
    void shouldCalculateMonthlyPayment() {
        // Given
        String loanId = "LOAN-001";
        Loan loan = createLoan(loanId, "CUST-001");
        loan.setAmount(new BigDecimal("12000.00"));
        loan.setInterestRate(new BigDecimal("0.10"));
        loan.setTermMonths(12);

        when(loanPersistencePort.findById(loanId)).thenReturn(Optional.of(loan));

        // When
        BigDecimal monthlyPayment = loanService.calculateMonthlyPayment(loanId);

        // Then
        Assertions.assertNotNull(monthlyPayment);
        Assertions.assertTrue(monthlyPayment.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName("Debería obtener préstamos próximos a vencer")
    void shouldGetLoansDueOn() {
        // Given
        LocalDate dueDate = LocalDate.now().plusDays(7);
        List<Loan> loans = Arrays.asList(createLoan("LOAN-001", "CUST-001"),
                                         createLoan("LOAN-002", "CUST-002"));

        when(loanPersistencePort.findByDueDate(dueDate)).thenReturn(loans);

        // When
        List<Loan> result = loanService.getLoansDueOn(dueDate);

        // Then
        Assertions.assertEquals(2, result.size());
    }

    private Loan createLoan(String loanId, String customerId) {
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        loan.setCustomerId(customerId);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.05"));
        loan.setTermMonths(12);
        loan.setStatus("PENDING");
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusMonths(12));
        return loan;
    }
}