package com.elixr.poc.rest.controller;

import com.elixr.poc.data.User;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.rest.response.UserResponse;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for User retrieval operation
 */

@RestController
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
        UserResponse userResponse = UserResponse.builder()
                .success(true).id(user.getId())
                .userName(user.getUserName()).firstName(user.getFirstName())
                .lastName(user.getLastName()).build();
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     * Retrieving user by username
     * calling getUserByName() method from service class
     * returning the UserResponse object
     * Handling the exception by GlobalExceptionHandler if any exception occurs
     * @param userName
     * @return
     */
    @GetMapping("/User/{userName}")
    public ResponseEntity<UserResponse> retrieveUserByName(@PathVariable("userName") @Valid String userName) {
        User existingUser = userService.getUserByName(userName);
        return new ResponseEntity<>(UserResponse.builder().success(true).id(existingUser.getId()).userName(existingUser.getUserName())
                .firstName(existingUser.getFirstName())
                .lastName(existingUser.getLastName()).build(), HttpStatus.OK);
    }

    /**
     * Retrieving all users
     * calling the getAllUses() method of service class
     * returning the response as list of all users through GetAllResponse object
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<GetAllResponse> getAllUser() {
        GetAllResponse allUsers = GetAllResponse.builder().success(true)
                .users(userService.getAllUsers()).build();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
