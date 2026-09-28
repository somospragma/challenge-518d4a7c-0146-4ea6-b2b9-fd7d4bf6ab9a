package com.fintech.loans.domain.exception;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Excepción específica para manejar solicitudes de préstamo inválidas.
 * Se lanza cuando los datos proporcionados para crear un préstamo no cumplen
 * con las reglas de negocio establecidas por la entidad financiera.
 * 
 * Esta excepción incluye información detallada sobre los errores de validación
 * para facilitar el diagnóstico y la corrección por parte del cliente.
 */
public class InvalidLoanRequestException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Código de error interno para categorización.
     */
    private final String errorCode;
    
    /**
     * Timestamp de cuando ocurrió el error.
     */
    private final LocalDateTime timestamp;
    
    /**
     * Mapa de errores de validación detallados.
     * Cada entrada representa un campo específico que failed la validación.
     */
    private final Map<String, String> validationErrors;
    
    /**
     * Constructor básico con mensaje de error.
     * @param message Descripción general del error
     */
    public InvalidLoanRequestException(String message) {
        super(message);
        this.errorCode = "LOAN_REQUEST_INVALID";
        this.timestamp = LocalDateTime.now();
        this.validationErrors = new HashMap<>();
    }
    
    /**
     * Constructor con código de error personalizado.
     * @param message Descripción del error
     * @param errorCode Código para categorización interna
     */
    public InvalidLoanRequestException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.validationErrors = new HashMap<>();
    }
    
    /**
     * Constructor completo con todos los detalles del error.
     * @param message Descripción del error
     * @param errorCode Código para categorización
     * @param cause Causa original de la excepción
     */
    public InvalidLoanRequestException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.validationErrors = new HashMap<>();
    }
    
    /**
     * Constructor con errores de validación específicos.
     * Útil cuando se detectan múltiples errores en los datos de entrada.
     * @param message Descripción general del error
     * @param errors Mapa de errores de validación campo -> descripción
     */
    public InvalidLoanRequestException(String message, Map<String, String> errors) {
        super(message);
        this.errorCode = "LOAN_REQUEST_VALIDATION_FAILED";
        this.timestamp = LocalDateTime.now();
        this.validationErrors = errors != null ? new HashMap<>(errors) : new HashMap<>();
    }
    
    /**
     * Constructor completo con causa y errores de validación.
     * @param message Descripción del error
     * @param errorCode Código para categorización
     * @param errors Mapa de errores de validación
     * @param cause Causa original
     */
    public InvalidLoanRequestException(String message, String errorCode, 
                                        Map<String, String> errors, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.validationErrors = errors != null ? new HashMap<>(errors) : new HashMap<>();
    }
    
    /**
     * Obtiene el código de error interno.
     * @return Código de error
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * Obtiene el timestamp de cuando ocurrió el error.
     * @return Fecha y hora del error
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Obtiene el mapa de errores de validación.
     * @return Copia del mapa de errores para evitar modificación
     */
    public Map<String, String> getValidationErrors() {
        return new HashMap<>(validationErrors);
    }
    
    /**
     * Agrega un error de validación específico.
     * @param field Campo que failed la validación
     * @param errorMessage Descripción del error
     */
    public void addValidationError(String field, String errorMessage) {
        this.validationErrors.put(field, errorMessage);
    }
    
    /**
     * Verifica si hay errores de validación específicos.
     * @return true si existen errores de validación registrados
     */
    public boolean hasValidationErrors() {
        return !validationErrors.isEmpty();
    }
    
    /**
     * Crea una excepción para monto de préstamo inválido.
     * @param amount Monto proporcionado
     * @param minAmount Monto mínimo permitido
     * @param maxAmount Monto máximo permitido
     * @return Nueva instancia de la excepción
     */
    public static InvalidLoanRequestException invalidAmount(BigDecimal amount, 
                                                            BigDecimal minAmount, 
                                                            BigDecimal maxAmount) {
        Map<String, String> errors = new HashMap<>();
        errors.put("amount", String.format("Monto %.2f fuera del rango válido [%s - %s]", 
                amount, minAmount, maxAmount));
        return new InvalidLoanRequestException(
            "El monto del préstamo debe estar entre " + minAmount + " y " + maxAmount,
            "INVALID_AMOUNT",
            errors
        );
    }
    
    /**
     * Crea una excepción para tasa de interés inválida.
     * @param rate Tasa proporcionada
     * @param minRate Tasa mínima permitida
     * @param maxRate Tasa máxima permitida
     * @return Nueva instancia de la excepción
     */
    public static InvalidLoanRequestException invalidInterestRate(BigDecimal rate, 
                                                                   BigDecimal minRate, 
                                                                   BigDecimal maxRate) {
        Map<String, String> errors = new HashMap<>();
        errors.put("interestRate", String.format("Tasa %.2f%% fuera del rango válido [%.2f%% - %.2f%%]", 
                rate, minRate, maxRate));
        return new InvalidLoanRequestException(
            "La tasa de interés debe estar entre " + minRate + "% y " + maxRate + "%",
            "INVALID_INTEREST_RATE",
            errors
        );
    }
    
    /**
     * Crea una excepción para plazo inválido.
     * @param term Plazo proporcionado
     * @param minTerm Plazo mínimo permitido
     * @param maxTerm Plazo máximo permitido
     * @return Nueva instancia de la excepción
     */
    public static InvalidLoanRequestException invalidTerm(int term, int minTerm, int maxTerm) {
        Map<String, String> errors = new HashMap<>();
        errors.put("termMonths", String.format("Plazo %d meses fuera del rango válido [%d - %d]", 
                term, minTerm, maxTerm));
        return new InvalidLoanRequestException(
            "El plazo del préstamo debe estar entre " + minTerm + " y " + maxTerm + " meses",
            "INVALID_TERM",
            errors
        );
    }
    
    /**
     * Crea una excepción para cliente inválido o no encontrado.
     * @param customerId Identificador del cliente
     * @return Nueva instancia de la excepción
     */
    public static InvalidLoanRequestException invalidCustomer(String customerId) {
        Map<String, String> errors = new HashMap<>();
        errors.put("customerId", "Cliente no válido o no encontrado: " + customerId);
        return new InvalidLoanRequestException(
            "El cliente especificado no es válido o no existe",
            "INVALID_CUSTOMER",
            errors
        );
    }
}