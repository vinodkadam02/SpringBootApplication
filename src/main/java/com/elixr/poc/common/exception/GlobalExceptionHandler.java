package com.elixr.poc.common.exception;
import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.rest.response.CommonResponse;
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
     * Handling the Exception and sending error message
     * Handles MethodArgumentNotValidException
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<PostErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorList = new ArrayList<>();
        for (final FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorList.add(error.getField() + " " + MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_MANDATORY_FIELD_MISSING.getKey()));
        }
        PostErrorResponse postErrorResponse = PostErrorResponse.builder().errorMessage(errorList).build();
        return new ResponseEntity<>(postErrorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * URL exceptions are handled.
     *
     * @param notFoundException
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CommonResponse> handleNotFoundException(NotFoundException notFoundException) {
        CommonResponse commonResponse = CommonResponse.builder().success(false)
                .errorMessage(notFoundException.getMessage()).build();
        return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Invalid format of UUID is handled.
     *
     * @param idFormatException
     * @return
     */
    @ExceptionHandler(IdFormatException.class)
    public ResponseEntity<CommonResponse> handleIdFormatException(IdFormatException idFormatException) {
        CommonResponse commonResponse = CommonResponse.builder().errorMessage(idFormatException.getMessage()).build();
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Generic exceptions are handled.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleGenericException(Exception exception) {
        CommonResponse commonResponse = CommonResponse.builder().success(false)
                .errorMessage(exception.getLocalizedMessage()).build();
        return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
