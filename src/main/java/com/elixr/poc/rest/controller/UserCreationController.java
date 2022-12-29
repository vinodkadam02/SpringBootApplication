package com.elixr.poc.rest.controller;

import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class UserCreationController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest newUser) {
        return UserService.builder().build().editResponse(newUser);
    }


}
