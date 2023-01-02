package com.elixr.poc.rest.controller;

import com.elixr.poc.constants.Constants;
import com.elixr.poc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
RestController for Delete API
 */
@RestController
@RequestMapping("/application")
public class UserDeletionController {

    private final UserService userService;

    public UserDeletionController(UserService userService) {
        this.userService = userService;
    }

    /*
    calling deleteUserDetails method with the parameter userId to delete the user by userId.
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUserDetails(userId);
        return new ResponseEntity<>(Constants.SUCCESSFULLY_DELETED, HttpStatus.OK);
    }
}
