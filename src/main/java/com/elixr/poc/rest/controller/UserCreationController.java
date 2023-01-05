package com.elixr.poc.rest.controller;

import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/application")
public class UserCreationController {

    private final UserService userService;
    public UserCreationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest newUser) {
        return  new ResponseEntity<>(userService.createValidUser(newUser), HttpStatus.OK);
    }
}