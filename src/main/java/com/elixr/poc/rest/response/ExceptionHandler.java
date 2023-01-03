package com.elixr.poc.rest.response;

import com.elixr.poc.rest.request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/*
Handling the exception and sending propper error message
 */
@ControllerAdvice
public class ExceptionHandler {
    private ErrorResponse errorResponse;


    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> myMessage(MethodArgumentNotValidException methodArgumentNotValidException) {
        return getResponseEntity(methodArgumentNotValidException);
    }

    private ResponseEntity<?> getResponseEntity(MethodArgumentNotValidException methodArgumentNotValidException) {
        errorResponse = new ErrorResponse();
        List<String> errorList = new ArrayList<>();
        for(final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()){
             errorList.add(error.getDefaultMessage());
        }
        errorResponse.setErrorMessage(errorList);
        errorResponse.setSuccess(false);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);


    }


}

