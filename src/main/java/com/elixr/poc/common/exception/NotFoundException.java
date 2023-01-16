package com.elixr.poc.common.exception;

/**
 * Handling the exception when there is incorrect ID
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
