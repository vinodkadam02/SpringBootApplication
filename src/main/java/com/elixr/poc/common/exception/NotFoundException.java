package com.elixr.poc.common.exception;

/**
 * Handling the exception when thier is incorrect URL
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
