package com.elixr.poc.rest.controller;

import com.elixr.poc.data.User;
import com.elixr.poc.service.UserRetrievalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class UserRetrievalController {

    private UserRetrievalService userRetrievalService;

    public UserRetrievalController(UserRetrievalService userRetrievalService) {
        this.userRetrievalService = userRetrievalService;
    }

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userRetrievalService.getAllUsers();
    }
}