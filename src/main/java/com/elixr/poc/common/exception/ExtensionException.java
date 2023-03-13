package com.elixr.poc.common.exception;

/**
 * Handling the exception when file extension does not end with ".csv" format.
 */
public class ExtensionException extends RuntimeException{
    public ExtensionException(String message){
        super(message);
    }
}
