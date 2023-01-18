package com.elixr.poc.common.exception;

/**
 * Handling the exception when ID is not is UUID format.
 */
public class IdFormatException extends IllegalArgumentException {
    public IdFormatException(String message){super(message);}
}
