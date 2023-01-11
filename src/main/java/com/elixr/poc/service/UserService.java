package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.User;
import com.elixr.poc.common.exception.GlobalException;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.response.AppResponse;
import com.elixr.poc.rest.response.GetAllResponse;
import com.elixr.poc.rest.response.PostErrorResponse;
import com.elixr.poc.rest.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
     * Deleting the user by the userId.
     * throwing a GlobalException to handel if the UserId is not present.
     */
    public boolean deleteUserDetails(UUID userId) throws GlobalException {
        boolean success = false;
        boolean userRecordExists = userRepository.existsById(userId);
        if (userRecordExists) {
            userRepository.deleteById(userId);
            success = true;
        } else {
            throw new GlobalException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_ID_NOT_EXISTS.getKey()));
        }
        return true;
    }

    /**
     * Creating a valid new user.
     *
     * @param user
     * @return
     */
    public AppResponse createValidUser(User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            return PostErrorResponse.builder().errorMessage(Collections.singletonList(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_EXISTS.getKey()))).build();
        } else {
            saveDataToDatabase(user);
            return UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName()).firstName(user.getFirstName()).lastName(user.getLastName()).build();
        }
    }

    /**
     * Calling the repository to store data
     *
     * @param user
     * @return
     */
    private void saveDataToDatabase(User user) {

        if (user.getId() == null || user.getId().toString().isEmpty()) {
            user.setId(UUID.randomUUID());
        }
        this.userRepository.save(user);
    }

    /**
     * Retriving all the users
     *
     * @return
     */
    public GetAllResponse getAllUsers() {
        return GetAllResponse.builder().success(true).users(userRepository.findAll()).build();
    }

    /**
     * Retrieve user by username.
     *
     * @param userName
     * @return
     */
    public User getUserByName(String userName) {
        User existingUser = userRepository.findByUserName(userName);
        return existingUser;
    }
}