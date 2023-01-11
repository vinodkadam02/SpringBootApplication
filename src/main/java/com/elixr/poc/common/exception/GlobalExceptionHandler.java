package com.elixr.poc.common.exception;

import com.elixr.poc.common.MessageKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.rest.response.CommonErrorResponse;
import com.elixr.poc.rest.response.PostErrorResponse;
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
     * Handle the execption and sending the error message
     *
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorList = new ArrayList<>();
        for (final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorList.add(error.getField()+" "+MessagesUtil.getMessage(MessageKeyEnum.ENTITY_MANDATORY_FIELD_MISSING.getKey()));
        }
        PostErrorResponse errorResponse = PostErrorResponse.builder().errorMessage(errorList).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * URL exceptions are handled.
     *
     * @param globalException
     * @return
     */

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity handleGlobalException(GlobalException globalException) {
        CommonErrorResponse commonErrorResponse = CommonErrorResponse.builder().success(false)
                .errorMessage(MessagesUtil.getMessage(MessageKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey())).build();
        return new ResponseEntity<>(commonErrorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Generic exceptions are handled.
     *
     * @param exception
     * @return
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception exception) {
        CommonErrorResponse commonErrorResponse = CommonErrorResponse.builder().success(false)
                .errorMessage(exception.getLocalizedMessage()).build();
        return new ResponseEntity<>(commonErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
