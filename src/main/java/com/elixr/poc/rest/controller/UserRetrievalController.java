package com.elixr.poc.rest.controller;

import com.elixr.poc.rest.response.GetAllResponse;
import com.elixr.poc.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for User retrieval operation.
 */

@RestController
@RequestMapping("/application")
public class UserRetrievalController {

    private final UserService userService;

    public UserRetrievalController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public GetAllResponse getAllUser() {
        return userService.getAllUsers();
    }
}