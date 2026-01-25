package com.tiagobarbosa.backend.exception;

public class CreditoNotFoundException extends RuntimeException {
    public CreditoNotFoundException(String message) {
        super(message);
    }
}
