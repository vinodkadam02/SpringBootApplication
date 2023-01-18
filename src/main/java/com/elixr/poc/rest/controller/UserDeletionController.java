package com.elixr.poc.rest.controller;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.rest.response.DeleteSuccessResponse;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserDeletionController {

    private final UserService userService;

    public UserDeletionController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Calling deleteUserDetails method with the parameter userId to delete the user by userId.
     * And handling the Exception if the userId is not matching.
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<DeleteSuccessResponse> deleteUser(@PathVariable("userId") @Valid String userId) {
        boolean success = userService.deleteUserDetails(userId);
        DeleteSuccessResponse deleteSuccessResponse = DeleteSuccessResponse.builder().success(success)
                .successMessage(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DELETED_SUCCESSFULLY.getKey())).build();
        return new ResponseEntity<>(deleteSuccessResponse, HttpStatus.OK);
    }
}
