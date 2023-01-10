package com.elixr.poc.exception;

import com.elixr.poc.rest.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handling the Exception and sending error message
     * Handles MethodArgumentNotValidException
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorList = new ArrayList<>();
        for (final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorList.add(error.getDefaultMessage());
        }
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(errorList)
                .success(false).build();
       PostErrorResponse errorResponse = PostErrorResponse.builder().errorMessage(errorList)
                .build();

        PostErrorResponse errorResponse = PostErrorResponse.builder().errorMessage(errorList).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
