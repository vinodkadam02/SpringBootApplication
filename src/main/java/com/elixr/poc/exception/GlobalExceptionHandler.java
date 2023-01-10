package com.elixr.poc.exception;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.rest.response.AppResponse;
import com.elixr.poc.rest.response.CommonErrorResponse;
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
     *
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorList = new ArrayList<>();
        for (final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorList.add(error.getDefaultMessage());
        }
        AppResponse errorResponse = AppResponse.builder().errorMessage(errorList).success(false).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * URL exceptions are handled
     *
     * @param noRecordFoundException
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity handleNoRecordFoundException(GlobalException noRecordFoundException) {
        CommonErrorResponse commonErrorResponse = CommonErrorResponse.builder().success(false).errorMessage(ApplicationConstants.ID_MISMATCH).build();
        return new ResponseEntity<>(commonErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Generic exceptions are handled
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception exception) {
        CommonErrorResponse commonErrorResponse = CommonErrorResponse.builder().success(false).errorMessage(exception.getLocalizedMessage()).build();
        return new ResponseEntity<>(commonErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
