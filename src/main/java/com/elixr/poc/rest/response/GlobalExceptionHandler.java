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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handelMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ErrorResponse errorResponse = new ErrorResponse();
        List<String> errorList = new ArrayList<>();
        for(final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()){
             errorList.add(error.getDefaultMessage());
        }
        errorResponse.setErrorMessage(errorList);
        errorResponse.setSuccess(false);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}

