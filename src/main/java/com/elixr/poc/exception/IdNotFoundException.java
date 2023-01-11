package com.elixr.poc.exception;

/**
 * Handling the exception when their is incorrect URL
 */
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }
}
