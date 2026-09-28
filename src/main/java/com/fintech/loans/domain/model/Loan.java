package com.fintech.loans.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad de dominio que representa un préstamo.
 * Contiene la lógica de negocio y validaciones asociadas.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private String loanId;
    private String customerId;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private int termMonths;
    private String status;
    private LocalDate startDate;
    private LocalDate dueDate;
    private BigDecimal monthlyPayment;

    /**
     * Calcula el pago mensual basado en el monto, tasa de interés y plazo.
     * Usa la fórmula de amortización francesa.
     * @return Monto del pago mensual
     */
    public BigDecimal calculateMonthlyPayment() {
        if (amount == null || interestRate == null || termMonths <= 0) {
            throw new IllegalArgumentException("Datos insuficientes para calcular pago mensual");
        }
        
        BigDecimal monthlyRate = interestRate.divide(BigDecimal.valueOf(12), 10, BigDecimal.ROUND_HALF_UP)
                                         .divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
        BigDecimal denominator = BigDecimal.ONE.subtract(
            BigDecimal.ONE.divide(BigDecimal.ONE.add(monthlyRate).pow(termMonths), 10, BigDecimal.ROUND_HALF_UP)
        );
        
        this.monthlyPayment = amount.multiply(monthlyRate).divide(denominator, 2, BigDecimal.ROUND_HALF_UP);
        return this.monthlyPayment;
    }

    /**
     * Calcula la fecha de vencimiento basada en la fecha de inicio y el plazo.
     * @return Fecha de vencimiento
     */
    public LocalDate calculateDueDate() {
        if (startDate == null) {
            throw new IllegalArgumentException("Fecha de inicio no puede ser nula");
        }
        this.dueDate = startDate.plusMonths(termMonths);
        return this.dueDate;
    }

    /**
     * Valida que el préstamo tenga datos consistentes.
     * @throws com.fintech.loans.domain.exception.InvalidLoanRequestException si hay datos inválidos
     */
    public void validate() {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID no puede ser vacío");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monto debe ser positivo");
        }
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Tasa de interés debe ser positiva");
        }
        if (termMonths <= 0) {
            throw new IllegalArgumentException("Plazo debe ser positivo");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Estado no puede ser vacío");
        }
    }

    /**
     * Genera un nuevo identificador único para el préstamo.
     */
    public void generateId() {
        this.loanId = UUID.randomUUID().toString();
    }
}