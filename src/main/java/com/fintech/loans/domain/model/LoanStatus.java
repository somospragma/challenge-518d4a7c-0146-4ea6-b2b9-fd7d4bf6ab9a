package com.fintech.loans.domain.model;

/**
 * Enumeración que define los estados posibles del ciclo de vida de un préstamo.
 * Cada estado representa una fase en el proceso de gestión del préstamo.
 */
public enum LoanStatus {
    /**
     * Estado inicial cuando el préstamo está siendo evaluado.
     * En este estado se validan los requisitos y se verifica la elegibilidad del cliente.
     */
    PENDING("Pendiente de revisión", true),
    
    /**
     * Estado cuando el préstamo ha sido aprobado y está activo.
     * El cliente ha cumplido con todos los requisitos y el préstamo está en vigor.
     */
    APPROVED("Aprobado", false),
    
    /**
     * Estado cuando el préstamo ha sido rechazado.
     * Puede ser por incumplimiento de requisitos, riesgo crediticio u otros factores.
     */
    REJECTED("Rechazado", false),
    
    /**
     * Estado cuando el préstamo está siendo pagado regularmente.
     * El cliente está al día con sus pagos mensuales.
     */
    ACTIVE("Activo", false),
    
    /**
     * Estado cuando el cliente ha incumplido con los pagos.
     * El préstamo está en mora y requiere gestión de cobranza.
     */
    DEFAULTED("En mora", false),
    
    /**
     * Estado cuando el préstamo ha sido completamente pagado.
     * Todas las cuotas han sido abonadas y no queda saldo pendiente.
     */
    PAID_OFF("Pagado", false),
    
    /**
     * Estado cuando el préstamo ha sido cancelado antes de completarse.
     * Puede ser por solicitud del cliente o por auditoría.
     */
    CANCELLED("Cancelado", false),
    
    /**
     * Estado temporal cuando el préstamo está en proceso de renovación.
     * Se está evaluando una extensión o refinanciamiento.
     */
    RENEGOTIATION("En renegociación", true);

    private final String displayName;
    private final boolean isTransitional;

    LoanStatus(String displayName, boolean isTransitional) {
        this.displayName = displayName;
        this.isTransitional = isTransitional;
    }

    /**
     * Obtiene el nombre para mostrar del estado.
     * @return Representación legible del estado
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Indica si el estado es transicional (temporal).
     * Los estados transicionales son aquellos por los que el préstamo pasa
     * durante su procesamiento pero no representan un estado final.
     * @return true si el estado es transicional
     */
    public boolean isTransitional() {
        return isTransitional;
    }

    /**
     * Verifica si el estado representa un estado terminal del préstamo.
     * Los estados terminales son aquellos donde el ciclo de vida del préstamo
     * ha finalizado (aprobado, rechazado, pagado, cancelado, en mora).
     * @return true si el estado es terminal
     */
    public boolean isTerminal() {
        return this == APPROVED || this == REJECTED || this == PAID_OFF 
               || this == CANCELLED || this == DEFAULTED;
    }

    /**
     * Verifica si el estado permite modificaciones en el préstamo.
     * Algunos estados no permiten cambios porque representan
     * estados finales o en proceso de auditoría.
     * @return true si el préstamo puede modificarse en este estado
     */
    public boolean allowsModification() {
        return this == PENDING || this == ACTIVE || this == RENEGOTIATION;
    }

    /**
     * Obtiene los estados válidos para transición desde el estado actual.
     * Define qué transiciones son permitidas en el flujo del préstamo.
     * @return Array de estados a los que se puede transitar
     */
    public LoanStatus[] getAllowedTransitions() {
        return switch (this) {
            case PENDING -> new LoanStatus[]{APPROVED, REJECTED, CANCELLED};
            case APPROVED -> new LoanStatus[]{ACTIVE, CANCELLED};
            case ACTIVE -> new LoanStatus[]{DEFAULTED, PAID_OFF, RENEGOTIATION};
            case DEFAULTED -> new LoanStatus[]{PAID_OFF, CANCELLED, RENEGOTIATION};
            case RENEGOTIATION -> new LoanStatus[]{ACTIVE, DEFAULTED, CANCELLED};
            case PAID_OFF, REJECTED, CANCELLED -> new LoanStatus[]{};
        };
    }

    /**
     * Valida si una transición a un nuevo estado es válida.
     * @param newStatus El estado al que se desea transitar
     * @return true si la transición es válida
     */
    public boolean canTransitionTo(LoanStatus newStatus) {
        if (newStatus == null) {
            return false;
        }
        LoanStatus[] allowed = getAllowedTransitions();
        for (LoanStatus status : allowed) {
            if (status == newStatus) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un estado por su nombre ignorando mayúsculas/minúsculas.
     * @param name Nombre del estado a buscar
     * @return El estado encontrado o null si no existe
     */
    public static LoanStatus findByName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        for (LoanStatus status : values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }

    /**
     * Obtiene el estado por su nombre para mostrar.
     * @param displayName Nombre para mostrar del estado
     * @return El estado encontrado o null si no existe
     */
    public static LoanStatus findByDisplayName(String displayName) {
        if (displayName == null || displayName.isBlank()) {
            return null;
        }
        for (LoanStatus status : values()) {
            if (status.displayName.equalsIgnoreCase(displayName)) {
                return status;
            }
        }
        return null;
    }
}