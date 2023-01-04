package com.elixr.poc.exception;

import java.util.UUID;

public class NoRecordFoundException extends Exception {
    public NoRecordFoundException(String message) {
        super(message);
    }
}
