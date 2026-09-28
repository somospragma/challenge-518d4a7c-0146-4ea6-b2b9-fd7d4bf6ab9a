package com.fintech.loans.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ConnectionErrorException extends RuntimeException {
    private final String serviceName;
    private final long retryAfterMs;

    public ConnectionErrorException(String message) {
        super(message);
        this.serviceName = "UNKNOWN";
        this.retryAfterMs = 0L;
    }

    public ConnectionErrorException(String message, Throwable cause) {
        super(message, cause);
        this.serviceName = "UNKNOWN";
        this.retryAfterMs = 0L;
    }

    public ConnectionErrorException(String message, String serviceName, long retryAfterMs) {
        super(message);
        this.serviceName = serviceName;
        this.retryAfterMs = retryAfterMs;
    }

    public ConnectionErrorException(String message, String serviceName, long retryAfterMs, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceName;
        this.retryAfterMs = retryAfterMs;
    }

    public String getServiceName() {
        return serviceName;
    }

    public long getRetryAfterMs() {
        return retryAfterMs;
    }

    public boolean isRetryable() {
        return retryAfterMs > 0;
    }

    public String getDetailedMessage() {
        return String.format("ConnectionErrorException{service='%s', retryAfterMs=%d, message='%s'}",
                serviceName, retryAfterMs, getMessage());
    }
}