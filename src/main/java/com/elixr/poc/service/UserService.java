package com.elixr.poc.service;

import com.elixr.poc.data.User;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.rest.response.ErrorResponse;
import com.elixr.poc.rest.response.UserResponse;
import com.elixr.poc.rest.validator.UserValidator;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/* service class for  editing response and save or update new user to database */
@Service
@Builder
public class UserService {

    /*
     edit the response by reading the request from user
     */
    public ResponseEntity<?> editResponse(UserRequest userRequestObject) {
        UserResponse responseObject;
        ErrorResponse userErrorResponse;
        List<String> errorList = UserValidator.builder().build().validate(userRequestObject);
        HttpStatus httpStatusCode = HttpStatus.OK;
        if (!errorList.isEmpty()) {
            userErrorResponse = ErrorResponse.builder().success(false).errorMessages(errorList).build();
            httpStatusCode = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(userErrorResponse, httpStatusCode);

        }
        User userObject = createUserObjectFromRequest(userRequestObject);
        responseObject = UserResponse.builder().success(true).id(userObject.getId()).userName(userObject.getUserName()).firstName(userObject.getFirstName()).lastName(userObject.getLastName()).build();
        return new ResponseEntity<>(responseObject, httpStatusCode);

    }

    private User createUserObjectFromRequest(UserRequest userRequest) {
        return User.builder().id(UUID.randomUUID()).userName(userRequest.getUserName()).firstName(userRequest.getFirstName()).lastName(userRequest.getLastName()).build();
    }


    /* to do
    calling the repository to persist the user object in db that is in scope of another jira INTTNG-201
    so this method will be refactored as part of that jira ticket.
     */

    public User saveOrUpdate(User user) {
        if (user.getId() == null || user.getId().toString().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            user.setId(uuid);
        }
        return user;

    }
}
