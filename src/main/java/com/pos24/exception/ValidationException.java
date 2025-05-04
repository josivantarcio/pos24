package com.pos24.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private final String field;
    private final String errorCode;
    
    public ValidationException(String message) {
        super(message);
        this.field = null;
        this.errorCode = null;
    }
    
    public ValidationException(String field, String message) {
        super(message);
        this.field = field;
        this.errorCode = null;
    }
    
    public ValidationException(String field, String message, String errorCode) {
        super(message);
        this.field = field;
        this.errorCode = errorCode;
    }
    
    public String getField() {
        return field;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
} 