package com.elixr.poc.common.exception;

/**
 * Handling the exception when thier is incorrect URL
 */
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }
}
