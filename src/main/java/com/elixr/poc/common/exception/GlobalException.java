package com.elixr.poc.common.exception;

/**
 * Handling the exception when there is incorrect URL.
 */
public class GlobalException extends RuntimeException {
    public GlobalException(String message) {
        super(message);
    }
}
