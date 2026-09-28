package com.fintech.loans.infrastructure.config;

import com.fintech.loans.domain.exception.ConnectionErrorException;
import com.fintech.loans.domain.exception.DatabaseUnavailableException;
import com.fintech.loans.domain.exception.InvalidLoanRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidLoanRequestException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidLoanRequestException(
            InvalidLoanRequestException ex, WebRequest request) {
        logger.warn("Solicitud de préstamo inválida: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(DatabaseUnavailableException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseUnavailableException(
            DatabaseUnavailableException ex, WebRequest request) {
        logger.error("Base de datos no disponible: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, 
                "La base de datos no está disponible en este momento. Por favor, intente más tarde.",
                request.getDescription(false));
    }

    @ExceptionHandler(ConnectionErrorException.class)
    public ResponseEntity<Map<String, Object>> handleConnectionErrorException(
            ConnectionErrorException ex, WebRequest request) {
        logger.error("Error de conexión: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.GATEWAY_TIMEOUT,
                "Error de conexión con el servidor externo. Por favor, intente más tarde.",
                request.getDescription(false));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento ilegal: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ha ocurrido un error inesperado. Por favor, contacte al administrador.",
                request.getDescription(false));
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            HttpStatus status, String message, String path) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now().toString());
        errorDetails.put("status", status.value());
        errorDetails.put("error", status.getReasonPhrase());
        errorDetails.put("message", message);
        errorDetails.put("path", path);
        return new ResponseEntity<>(errorDetails, status);
    }
}