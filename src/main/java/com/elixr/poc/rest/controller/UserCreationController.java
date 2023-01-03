package com.elixr.poc.rest.controller;

import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.rest.response.ExceptionHandler;
import com.elixr.poc.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/application")
public class UserCreationController {

    private final UserService userService;
    private UserRequest userRequest;
    private ExceptionHandler exceptionHandler;

    public UserCreationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest newUser) {
        userRequest = newUser;
        return  new ResponseEntity<>(userService.sendResponse(newUser), HttpStatus.OK);
    }

    /*
    Handling the Exception and sending error message
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        exceptionHandler = new ExceptionHandler();
        return exceptionHandler.myMessage(methodArgumentNotValidException);
    }
}
