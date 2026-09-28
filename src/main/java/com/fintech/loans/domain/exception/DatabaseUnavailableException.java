package com.fintech.loans.domain.exception;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Excepción específica para manejar errores de disponibilidad de la base de datos.
 * Se lanza cuando la aplicación no puede establecer conexión con la base de datos
 * o cuando una operación de base de datos falla por razones de conectividad,
 * timeouts o errores de servidor.
 * 
 * Esta excepción proporciona información detallada sobre el tipo de error de base de datos
 * para facilitar el diagnóstico y las acciones de recuperación.
 */
public class DatabaseUnavailableException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Enumeración de tipos de errores de base de datos.
     */
    public enum DatabaseErrorType {
        CONNECTION_TIMEOUT("Tiempo de conexión agotado", "No se pudo establecer conexión con la base de datos"),
        CONNECTION_REFUSED("Conexión rechazada", "El servidor de base de datos rechazó la conexión"),
        SERVER_UNAVAILABLE("Servidor no disponible", "El servidor de base de datos no está disponible"),
        QUERY_TIMEOUT("Tiempo de consulta agotado", "La consulta excedió el tiempo máximo de ejecución"),
        DEADLOCK("Deadlock detectado", "Se detectó un deadlock en la base de datos"),
        POOL_EXHAUSTED("Pool de conexiones agotado", "No hay conexiones disponibles en el pool"),
        SCHEMA_ERROR("Error de esquema", "El esquema de la base de datos no es válido"),
        DATA_INTEGRITY("Violación de integridad", "La operación viola restricciones de integridad"),
        UNKNOWN("Error desconocido", "Error de base de datos no categorizado");
        
        private final String title;
        private final String description;
        
        DatabaseErrorType(String title, String description) {
            this.title = title;
            this.description = description;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    private final DatabaseErrorType errorType;
    private final LocalDateTime timestamp;
    private final String sqlState;
    private final int vendorCode;
    private final String query;
    private final Map<String, Object> context;
    
    /**
     * Constructor básico con mensaje de error.
     * @param message Descripción del error
     */
    public DatabaseUnavailableException(String message) {
        super(message);
        this.errorType = DatabaseErrorType.UNKNOWN;
        this.timestamp = LocalDateTime.now();
        this.sqlState = null;
        this.vendorCode = 0;
        this.query = null;
        this.context = new HashMap<>();
    }
    
    /**
     * Constructor con tipo de error específico.
     * @param message Descripción del error
     * @param errorType Tipo de error de base de datos
     */
    public DatabaseUnavailableException(String message, DatabaseErrorType errorType) {
        super(message);
        this.errorType = errorType != null ? errorType : DatabaseErrorType.UNKNOWN;
        this.timestamp = LocalDateTime.now();
        this.sqlState = null;
        this.vendorCode = 0;
        this.query = null;
        this.context = new HashMap<>();
    }
    
    /**
     * Constructor con causa original del error.
     * @param message Descripción del error
     * @param cause Excepción original que causó el error
     */
    public DatabaseUnavailableException(String message, Throwable cause) {
        super(message, cause);
        this.errorType = classifyError(cause);
        this.timestamp = LocalDateTime.now();
        this.sqlState = extractSqlState(cause);
        this.vendorCode = extractVendorCode(cause);
        this.query = null;
        this.context = new HashMap<>();
    }
    
    /**
     * Constructor completo con todos los detalles del error.
     * @param message Descripción del error
     * @param errorType Tipo de error de base de datos
     * @param cause Excepción original
     */
    public DatabaseUnavailableException(String message, DatabaseErrorType errorType, Throwable cause) {
        super(message, cause);
        this.errorType = errorType != null ? errorType : classifyError(cause);
        this.timestamp = LocalDateTime.now();
        this.sqlState = extractSqlState(cause);
        this.vendorCode = extractVendorCode(cause);
        this.query = null;
        this.context = new HashMap<>();
    }
    
    /**
     * Constructor con información de la consulta que falló.
     * @param message Descripción del error
     * @param errorType Tipo de error
     * @param query La consulta SQL que falló
     * @param cause Causa original
     */
    public DatabaseUnavailableException(String message, DatabaseErrorType errorType, 
                                         String query, Throwable cause) {
        super(message, cause);
        this.errorType = errorType != null ? errorType : classifyError(cause);
        this.timestamp = LocalDateTime.now();
        this.sqlState = extractSqlState(cause);
        this.vendorCode = extractVendorCode(cause);
        this.query = query;
        this.context = new HashMap<>();
    }
    
    /**
     * Clasifica el tipo de error basándose en la excepción original.
     * @param cause Excepción original
     * @return Tipo de error clasificado
     */
    private static DatabaseErrorType classifyError(Throwable cause) {
        if (cause == null) {
            return DatabaseErrorType.UNKNOWN;
        }
        
        String exceptionClass = cause.getClass().getSimpleName().toLowerCase();
        String message = cause.getMessage() != null ? cause.getMessage().toLowerCase() : "";
        
        if (exceptionClass.contains("timeout") || message.contains("timeout")) {
            if (message.contains("connection")) {
                return DatabaseErrorType.CONNECTION_TIMEOUT;
            }
            return DatabaseErrorType.QUERY_TIMEOUT;
        }
        
        if (exceptionClass.contains("refused") || message.contains("refused")) {
            return DatabaseErrorType.CONNECTION_REFUSED;
        }
        
        if (exceptionClass.contains("unavailable") || message.contains("unavailable")) {
            return DatabaseErrorType.SERVER_UNAVAILABLE;
        }
        
        if (exceptionClass.contains("deadlock") || message.contains("deadlock")) {
            return DatabaseErrorType.DEADLOCK;
        }
        
        if (exceptionClass.contains("pool") || message.contains("pool")) {
            return DatabaseErrorType.POOL_EXHAUSTED;
        }
        
        if (exceptionClass.contains("integrity") || exceptionClass.contains("constraint")) {
            return DatabaseErrorType.DATA_INTEGRITY;
        }
        
        return DatabaseErrorType.UNKNOWN;
    }
    
    /**
     * Extrae el SQLState de la excepción si está disponible.
     * @param cause Excepción original
     * @return SQLState o null
     */
    private static String extractSqlState(Throwable cause) {
        if (cause instanceof SQLException) {
            return ((SQLException) cause).getSQLState();
        }
        return null;
    }
    
    /**
     * Extrae el código de error del vendor de la excepción.
     * @param cause Excepción original
     * @return Código de vendor o 0
     */
    private static int extractVendorCode(Throwable cause) {
        if (cause instanceof SQLException) {
            return ((SQLException) cause).getErrorCode();
        }
        return 0;
    }
    
    /**
     * Obtiene el tipo de error de base de datos.
     * @return Tipo de error
     */
    public DatabaseErrorType getErrorType() {
        return errorType;
    }
    
    /**
     * Obtiene el timestamp de cuando ocurrió el error.
     * @return Fecha y hora del error
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Obtiene el SQLState de la excepción de base de datos.
     * @return SQLState o null
     */
    public String getSqlState() {
        return sqlState;
    }
    
    /**
     * Obtiene el código de error del vendor.
     * @return Código de vendor
     */
    public int getVendorCode() {
        return vendorCode;
    }
    
    /**
     * Obtiene la consulta SQL que provocó el error.
     * @return Consulta o null
     */
    public String getQuery() {
        return query;
    }
    
    /**
     * Obtiene el contexto adicional del error.
     * @return Mapa de contexto
     */
    public Map<String, Object> getContext() {
        return new HashMap<>(context);
    }
    
    /**
     * Agrega información de contexto al error.
     * @param key Clave del contexto
     * @param value Valor del contexto
     */
    public void addContext(String key, Object value) {
        this.context.put(key, value);
    }
    
    /**
     * Verifica si el error es de conexión.
     * @return true si es un error de conexión
     */
    public boolean isConnectionError() {
        return errorType == DatabaseErrorType.CONNECTION_TIMEOUT ||
               errorType == DatabaseErrorType.CONNECTION_REFUSED ||
               errorType == DatabaseErrorType.SERVER_UNAVAILABLE ||
               errorType == DatabaseErrorType.POOL_EXHAUSTED;
    }
    
    /**
     * Verifica si el error es recuperable.
     * Algunos errores pueden ser temporales y la operación puede reintentarse.
     * @return true si el error es potencialmente recuperable
     */
    public boolean isRecoverable() {
        return isConnectionError() || 
               errorType == DatabaseErrorType.DEADLOCK ||
               errorType == DatabaseErrorType.QUERY_TIMEOUT;
    }
    
    /**
     * Crea una excepción para conexión agotada.
     * @return Nueva instancia de la excepción
     */
    public static DatabaseUnavailableException connectionTimeout() {
        return new DatabaseUnavailableException(
            "Tiempo de conexión con la base de datos agotado",
            DatabaseErrorType.CONNECTION_TIMEOUT
        );
    }
    
    /**
     * Crea una excepción para servidor no disponible.
     * @return Nueva instancia de la excepción
     */
    public static DatabaseUnavailableException serverUnavailable() {
        return new DatabaseUnavailableException(
            "El servidor de base de datos no está disponible",
            DatabaseErrorType.SERVER_UNAVAILABLE
        );
    }
    
    /**
     * Crea una excepción con información de la consulta.
     * @param query Consulta que falló
     * @param cause Causa original
     * @return Nueva instancia
     */
    public static DatabaseUnavailableException queryFailed(String query, Throwable cause) {
        DatabaseUnavailableException ex = new DatabaseUnavailableException(
            "Error al ejecutar la consulta en la base de datos",
            DatabaseErrorType.UNKNOWN,
            query,
            cause
        );
        ex.addContext("sql", query);
        return ex;
    }
}