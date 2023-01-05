package com.elixr.poc.rest.controller;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.exception.NoRecordFoundException;
import com.elixr.poc.rest.response.CommonErrorResponse;
import com.elixr.poc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/application")
public class UserDeletionController {

    private final UserService userService;

    public UserDeletionController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Calling deleteUserDetails method with the parameter userId to delete the user by userId.
     * And handling the Exception if the userId is not matching.
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") UUID userId) throws NoRecordFoundException {
        CommonErrorResponse deleteResponse;
        HttpStatus httpStatus;
        try {
            boolean success = userService.deleteUserDetails(userId);
            deleteResponse = CommonErrorResponse.builder().success(success)
                    .errorMessage(ApplicationConstants.SUCCESSFULLY_DELETED).build();
            httpStatus = HttpStatus.OK;
        } catch (NoRecordFoundException noRecordFoundException) {
            throw noRecordFoundException;
        }
        return new ResponseEntity<>(deleteResponse, httpStatus);
    }
}
