package com.fintech.loans.application.ports.out;

import com.fintech.loans.domain.model.Loan;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

/**
 * Puerto de salida para operaciones de persistencia de préstamos.
 * Define los contratos que la capa de aplicación necesita de la infraestructura.
 */
public interface LoanPersistencePort {
    /**
     * Guarda un préstamo en el repositorio.
     * @param loan Préstamo a guardar
     * @return Préstamo guardado
     * @throws com.fintech.loans.domain.exception.DatabaseUnavailableException si hay error de conexión
     */
    Loan save(Loan loan);

    /**
     * Busca un préstamo por su identificador.
     * @param loanId Identificador del préstamo
     * @return Optional con el préstamo si existe
     */
    Optional<Loan> findById(String loanId);

    /**
     * Busca todos los préstamos de un cliente.
     * @param customerId Identificador del cliente
     * @return Lista de préstamos
     */
    List<Loan> findByCustomerId(String customerId);

    /**
     * Elimina un préstamo.
     * @param loanId Identificador del préstamo
     */
    void deleteById(String loanId);

    /**
     * Busca préstamos que vencen en una fecha específica.
     * @param dueDate Fecha de vencimiento
     * @return Lista de préstamos
     */
    List<Loan> findByDueDate(LocalDate dueDate);

    /**
     * Actualiza el estado de un préstamo.
     * @param loanId Identificador del préstamo
     * @param newStatus Nuevo estado
     * @return Préstamo actualizado
     */
    Loan updateStatus(String loanId, String newStatus);
}