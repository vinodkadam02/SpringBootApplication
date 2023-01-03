package com.elixr.poc.rest.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class PurchaseExceptionHandler {
    private PurchaseErrorResponse errorResponse;

    /*Handling the Exception and sending error message*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> myMessage(MethodArgumentNotValidException methodArgumentNotValidException) {
        return getResponseEntity(methodArgumentNotValidException);
    }

    private ResponseEntity<?> getResponseEntity(MethodArgumentNotValidException methodArgumentNotValidException) {
        errorResponse = new PurchaseErrorResponse();
        List<String> errorList = new ArrayList<>();
        for (final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorList.add(error.getDefaultMessage());
        }
        errorResponse.setErrorMessage(errorList);
        errorResponse.setSuccess(false);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
