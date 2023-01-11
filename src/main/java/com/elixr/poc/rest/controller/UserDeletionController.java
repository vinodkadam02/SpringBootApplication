package com.elixr.poc.rest.controller;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdNotFoundException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.rest.response.CommonResponse;
import com.elixr.poc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * RestController for Delete API
 */
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
    public ResponseEntity<?> deleteUser(@PathVariable("userId") UUID userId) throws IdNotFoundException {
        CommonResponse commonResponse;
        HttpStatus httpStatus;
        boolean success = userService.deleteUserDetails(userId);
        commonResponse = CommonResponse.builder().success(success).errorMessage(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DELETED_SUCCESSFULLY.getKey())).build();
        httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(commonResponse, httpStatus);
    }
}
