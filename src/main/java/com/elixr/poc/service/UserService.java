package com.elixr.poc.service;

import com.elixr.poc.data.User;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.rest.response.UserGetResponse;
import com.elixr.poc.rest.response.UserPostResponse;
import com.elixr.poc.rest.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class to interact with controllers for all user related operations
 */
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    /**
     * Creating a valid user
     * @param userRequestObject
     * @return
     */
    public UserPostResponse createValidUser(UserRequest userRequestObject) {
        User userObject = createUserObjectFromRequest(userRequestObject);
        saveDataToDatabase(userObject);
        return UserPostResponse.newBuilder().success(true).id(userObject.getId()).userName(userObject.getUserName()).firstName(userObject.getFirstName()).lastName(userObject.getLastName()).build();
    }

    private User createUserObjectFromRequest(UserRequest userRequest) {
        return User.builder().userName(userRequest.getUserName()).firstName(userRequest.getFirstName()).lastName(userRequest.getLastName()).build();
    }

    /**
     * Calling the repository to store data
     * @param user
     * @return
     */
    private User saveDataToDatabase(User user) {

        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(String.valueOf(UUID.randomUUID()));
        }
        user = this.userRepository.save(user);
        return user;
    }


    /**
     * Retriving all the users
     * @return
     */
    public UserGetResponse getAllUsers() {

        return  UserGetResponse.newGetBuilder().success(true).users(userRepository.findAll()).build();
    }
}
