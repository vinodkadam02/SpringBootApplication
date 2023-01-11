package com.elixr.poc.rest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
Handling the exception and sending proper error message
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorList = new ArrayList<>();
        for(final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()){
             errorList.add(error.getDefaultMessage());
        }
    AppResponse appResponse = AppResponse.builder().errorMessage(errorList).build();
        return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
    }
}
