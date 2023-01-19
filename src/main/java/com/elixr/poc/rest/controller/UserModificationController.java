package com.elixr.poc.rest.controller;

import com.elixr.poc.data.User;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class UserModificationController {
    private final UserService userService;

    public UserModificationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Calling a method updateUserPartially of service class.
     * Returning the response.
     * And handling the Exception if the userId is not matching.
     * @param userId
     * @param userDetails
     * @return
     */
    @PatchMapping("/user/{userId}")
    public ResponseEntity<User> modifyUser(@PathVariable("userId") String userId, @RequestBody @Valid UserRequest userDetails){
        return new ResponseEntity<>(userService.updateUserPartially(userId, userDetails), HttpStatus.OK);
    }
}
