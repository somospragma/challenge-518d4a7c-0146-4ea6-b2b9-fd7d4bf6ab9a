package com.fintech.loans.application.ports.in;

import com.fintech.loans.domain.model.Loan;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Puerto de entrada para operaciones de negocio relacionadas con préstamos.
 * Define los contratos que la capa de aplicación expone al exterior.
 */
public interface LoanServicePort {
    /**
     * Crea un nuevo préstamo con los datos proporcionados.
     * @param customerId Identificador del cliente
     * @param amount Monto del préstamo
     * @param interestRate Tasa de interés anual
     * @param termMeses Plazo en meses
     * @return Loan creado con estado y fechas calculadas
     * @throws com.fintech.loans.domain.exception.InvalidLoanRequestException si los datos son inválidos
     */
    Loan createLoan(String customerId, BigDecimal amount, BigDecimal interestRate, int termMeses);

    /**
     * Obtiene un préstamo por su identificador único.
     * @param loanId Identificador del préstamo
     * @return Optional con el préstamo si existe
     */
    Optional<Loan> getLoanById(String loanId);

    /**
     * Obtiene todos los préstamos de un cliente.
     * @param customerId Identificador del cliente
     * @return Lista de préstamos activos
     */
    List<Loan> getLoansByCustomer(String customerId);

    /**
     * Actualiza el estado de un préstamo.
     * @param loanId Identificador del préstamo
     * @param newStatus Nuevo estado
     * @return Loan actualizado
     * @throws com.fintech.loans.domain.exception.InvalidLoanRequestException si el estado no es válido
     */
    Loan updateLoanStatus(String loanId, String newStatus);

    /**
     * Calcula el pago mensual para un préstamo.
     * @param loanId Identificador del préstamo
     * @return Monto del pago mensual
     * @throws com.fintech.loans.domain.exception.InvalidLoanRequestException si el préstamo no existe
     */
    BigDecimal calculateMonthlyPayment(String loanId);

    /**
     * Obtiene préstamos que vencen en una fecha específica.
     * @param dueDate Fecha de vencimiento
     * @return Lista de préstamos que vencen en esa fecha
     */
    List<Loan> getLoansDueOn(LocalDate dueDate);
}