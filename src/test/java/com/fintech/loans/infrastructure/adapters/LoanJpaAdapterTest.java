package com.fintech.loans.infrastructure.adapters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fintech.loans.domain.exception.DatabaseUnavailableException;
import com.fintech.loans.domain.model.Loan;
import com.fintech.loans.infrastructure.persistence.LoanRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("LoanJpaAdapter - Tests del adaptador de persistencia JPA")
@ExtendWith(MockitoExtension.class)
class LoanJpaAdapterTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanJpaAdapter loanJpaAdapter;

    @Test
    @DisplayName("Debería guardar un préstamo exitosamente")
    void shouldSaveLoanSuccessfully() {
        // Given
        Loan loan = createLoan("LOAN-001", "CUST-001");
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // When
        Loan result = loanJpaAdapter.save(loan);

        // Then
        assertNotNull(result);
        assertEquals("LOAN-001", result.getLoanId());
        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    @DisplayName("Debería buscar un préstamo por ID")
    void shouldFindLoanById() {
        // Given
        String loanId = "LOAN-001";
        Loan loan = createLoan(loanId, "CUST-001");
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        // When
        Optional<Loan> result = loanJpaAdapter.findById(loanId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(loanId, result.get().getLoanId());
    }

    @Test
    @DisplayName("Debería buscar préstamos por ID de cliente")
    void shouldFindLoansByCustomerId() {
        // Given
        String customerId = "CUST-001";
        List<Loan> loans = Arrays.asList(
            createLoan("LOAN-001", customerId),
            createLoan("LOAN-002", customerId)
        );
        when(loanRepository.findByCustomerId(customerId)).thenReturn(loans);

        // When
        List<Loan> result = loanJpaAdapter.findByCustomerId(customerId);

        // Then
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Debería eliminar un préstamo por ID")
    void shouldDeleteLoanById() {
        // Given
        String loanId = "LOAN-001";
        doNothing().when(loanRepository).deleteById(loanId);

        // When
        loanJpaAdapter.deleteById(loanId);

        // Then
        verify(loanRepository, times(1)).deleteById(loanId);
    }

    @Test
    @DisplayName("Debería buscar préstamos por fecha de vencimiento")
    void shouldFindLoansByDueDate() {
        // Given
        LocalDate dueDate = LocalDate.now().plusDays(7);
        List<Loan> loans = Arrays.asList(
            createLoan("LOAN-001", "CUST-001"),
            createLoan("LOAN-002", "CUST-002")
        );
        when(loanRepository.findByDueDate(dueDate)).thenReturn(loans);

        // When
        List<Loan> result = loanJpaAdapter.findByDueDate(dueDate);

        // Then
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Debería actualizar el estado de un préstamo")
    void shouldUpdateLoanStatus() {
        // Given
        String loanId = "LOAN-001";
        String newStatus = "APPROVED";
        Loan loan = createLoan(loanId, "CUST-001");
        loan.setStatus(newStatus);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // When
        Loan result = loanJpaAdapter.updateStatus(loanId, newStatus);

        // Then
        assertNotNull(result);
        assertEquals(newStatus, result.getStatus());
    }

    @Test
    @DisplayName("Debería manejar error de base de datos al guardar")
    void shouldHandleDatabaseErrorOnSave() {
        // Given
        Loan loan = createLoan("LOAN-001", "CUST-001");
        when(loanRepository.save(any(Loan.class)))
            .thenThrow(new RuntimeException("Database connection failed"));

        // When & Then
        assertThrows(DatabaseUnavailableException.class, () -> loanJpaAdapter.save(loan));
    }

    @Test
    @DisplayName("Debería manejar error al buscar por ID")
    void shouldHandleErrorOnFindById() {
        // Given
        String loanId = "LOAN-001";
        when(loanRepository.findById(loanId))
            .thenThrow(new RuntimeException("Database unavailable"));

        // When & Then
        assertThrows(DatabaseUnavailableException.class, () -> loanJpaAdapter.findById(loanId));
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
        loan.setMonthlyPayment(new BigDecimal("880.00"));
        return loan;
    }
}