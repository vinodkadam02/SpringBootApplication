package com.elixr.poc.service;

import com.elixr.poc.data.User;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.response.UserGetResponse;
import com.elixr.poc.rest.response.UserPostResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class to interact with controllers for all user related operations
 */

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    /**
     * Creating a valid user
     * @param newUser
     * @return
     */

    public UserPostResponse createValidUser(User newUser) {
        saveDataToDatabase(newUser);
        return UserPostResponse.newBuilder().id(newUser.getId()).userName(newUser.getUserName()).firstName(newUser.getFirstName()).lastName(newUser.getLastName()).build();
    }

    /**
     * Calling the repository to store data
     * @param user
     * @return
     */

    private void saveDataToDatabase(User user) {

        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(String.valueOf(UUID.randomUUID()));
        }
        this.userRepository.save(user);
    }

    /**
     * Retriving all the users
     * @return
     */

    public UserGetResponse getAllUsers() {
        return UserGetResponse.newGetBuilder().users(userRepository.findAll()).build();
    }
}
