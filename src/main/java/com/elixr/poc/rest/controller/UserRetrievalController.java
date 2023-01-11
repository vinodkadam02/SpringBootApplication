package com.elixr.poc.rest.controller;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.User;
import com.elixr.poc.rest.response.CommonResponse;
import com.elixr.poc.rest.response.GetAllResponse;
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

    @GetMapping("/users")
    public GetAllResponse getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity retrieveUser(@PathVariable("userName") @Valid String userName) {
        User user = userService.getUserByName(userName);
        if (user != null) {
            return new ResponseEntity(UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName()).firstName(user.getFirstName()).lastName(user.getLastName()).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonResponse.builder().success(false).errorMessage(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_NOT_EXISTS.getKey())).build(), HttpStatus.NOT_FOUND);
        }
    }
}