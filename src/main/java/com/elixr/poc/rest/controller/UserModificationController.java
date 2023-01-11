package com.elixr.poc.rest.controller;

import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@RequestMapping("/application")
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
    public ResponseEntity modifyUser(@PathVariable("userId") UUID userId, @RequestBody @Valid UserRequest userDetails){
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(userService.updateUserPartially(userId, userDetails), httpStatus);
    }
}
