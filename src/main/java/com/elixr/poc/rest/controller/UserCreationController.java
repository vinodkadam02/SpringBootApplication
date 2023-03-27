package com.elixr.poc.rest.controller;

import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.User;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.rest.response.PostErrorResponse;
import com.elixr.poc.rest.response.UserResponse;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@Validated
public class UserCreationController {
    private final UserService userService;

    public UserCreationController(UserService userService) {

        this.userService = userService;
    }

    /**
     * Calling service class method to create new valid user
     * @param userRequest
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody @Valid UserRequest userRequest) {
        User user = User.builder().userName(userRequest.getUserName()).firstName(userRequest.getFirstName()).lastName(userRequest.getLastName()).build();
        user = userService.createValidUser(user);
        if (user == null){
            return new ResponseEntity<>(PostErrorResponse.builder().success(false).errorMessage(Collections.singletonList(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_EXISTS.getKey()))).build(),HttpStatus.BAD_REQUEST);
        }
      return new ResponseEntity<>(UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName())
                .firstName(user.getFirstName()).lastName(user.getLastName()).build(), HttpStatus.OK);
    }
}