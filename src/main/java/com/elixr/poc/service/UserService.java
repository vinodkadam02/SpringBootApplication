package com.elixr.poc.service;

import com.elixr.poc.data.User;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.rest.response.ExceptionHandler;
import com.elixr.poc.rest.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/* service class for  editing response and save or update new user to database */
@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository saveDataToRepository) {

        this.userRepository = saveDataToRepository;
    }

    /* Sending the successful response to user */
    public UserResponse sendResponse(UserRequest userRequestObject) {
        User userObject = createUserObjectFromRequest(userRequestObject);
        saveDataToDatabase(userObject);
        return UserResponse.builder().success(true).id(userObject.getId()).userName(userObject.getUserName()).firstName(userObject.getFirstName()).lastName(userObject.getLastName()).build();
    }

    private User createUserObjectFromRequest(UserRequest userRequest) {
        User user = User.builder().userName(userRequest.getUserName()).firstName(userRequest.getFirstName()).lastName(userRequest.getLastName()).build();
        return user;
    }

    /*
    calling UserRepository to store the valid user information to the database
     */
    private User saveDataToDatabase(User user) {

        if (user.getId() == null || user.getId().toString().isEmpty()) {
            user.setId(UUID.randomUUID());
        }
        user = this.userRepository.save(user);
        return user;
    }
}
