package com.elixr.poc.exception;

/**
 * Handling the exception when there is incorrect URL.
 */
public class GlobalException extends RuntimeException {
    public GlobalException(String message) {
        super(message);
    }
}
