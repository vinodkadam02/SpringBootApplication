package com.elixr.poc.rest.controller;

import com.elixr.poc.data.User;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.rest.response.AppResponse;
import com.elixr.poc.rest.response.UserResponse;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for User retrieval operation
 */

@RestController
@RequestMapping("/application")
public class UserRetrievalController {

    private final UserService userService;

    public UserRetrievalController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Getting the user by userId.
     * Calling a method getUserByUserId of service class.
     * Returning the response.
     * And handling the Exception if the userId is not matching.
     *
     * @param userId
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponse> retrieveUser(@PathVariable("userId") @Valid String userId) {
        User user = userService.getUserByUserId(userId);
        UserResponse userResponse = UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName()).firstName(user.getFirstName()).lastName(user.getLastName()).build();
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/User/{userName}")
    public ResponseEntity<AppResponse> retrieveUserByName(@PathVariable("userName") @Valid String userName) {
        return new ResponseEntity<>(userService.getUserByName(userName), HttpStatus.OK);
    }
}
