package com.elixr.poc.rest.controller;

import com.elixr.poc.data.User;
import com.elixr.poc.exception.GlobalException;
import com.elixr.poc.rest.response.UserResponse;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller class for User retrieval operation
 */

@RestController
@Validated
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
     * @throws GlobalException
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity retrieveUser(@PathVariable("userId") @Valid UUID userId) throws GlobalException {
        HttpStatus httpStatus;
        try {
            User.builder().build().setId(userId);
            User user = userService.getUserByUserId(userId);
            UserResponse userResponse = UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName())
                    .firstName(user.getFirstName()).lastName(user.getLastName()).build();
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(userResponse, httpStatus);
        } catch (GlobalException globalException) {
            throw globalException;
        }
    }
}
