package com.elixr.poc.common.exception;

/**
 * Handling the exception when there is incorrect Id.
 */
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }
}
