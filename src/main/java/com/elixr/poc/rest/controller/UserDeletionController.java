package com.elixr.poc.rest.controller;

import com.elixr.poc.common.exception.IdNotFoundException;
import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.rest.response.DeleteResponse;
import com.elixr.poc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
RestController for Delete API
 */
@RestController
@RequestMapping("/application")
public class UserDeletionController {

    private final UserService userService;

    public UserDeletionController(UserService userService) {
        this.userService = userService;
    }

    /**
    Calling deleteUserDetails method with the parameter userId to delete the user by userId.
    And handling the Exception if the userId is not matching.
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") UUID userId) {
        DeleteResponse deleteResponse = new DeleteResponse();
        HttpStatus httpStatus;
        try {
            boolean success = userService.deleteUserDetails(userId);
            deleteResponse.setSuccess(success);
            deleteResponse.setErrorMessage(ApplicationConstants.SUCCESSFULLY_DELETED);
            httpStatus = HttpStatus.OK;
        } catch (IdNotFoundException e) {
            deleteResponse.setSuccess(false);
            deleteResponse.setErrorMessage(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(deleteResponse, httpStatus);
    }
}
