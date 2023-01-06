package com.elixr.poc.service;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.data.User;
import com.elixr.poc.exception.NoRecordFoundException;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.response.UserResponse;
import com.elixr.poc.rest.response.ErrorResponse;
import com.elixr.poc.rest.response.GetAllResponse;
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
     * Deleting the user by the userId.
     * throwing a NoRecordFoundException to handel if the UserId is not present.
     */
    public boolean deleteUserDetails(UUID userId) throws NoRecordFoundException {
        boolean success = false;
        boolean userRecordExists = userRepository.existsById(userId);
        if (userRecordExists) {
            userRepository.deleteById(userId);
            success = true;
        } else {
            throw new NoRecordFoundException(ApplicationConstants.ID_MISMATCH);
        }
        return true;
    }

    /**
     * Creating a valid user
     * @param user
     * @return
     */
    public ErrorResponse createValidUser(User user) {
        saveDataToDatabase(user);
        return UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName()).firstName(user.getFirstName()).lastName(user.getLastName()).build();
    }

    /**
     * Calling the repository to store data
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
     * @return
     */
    public GetAllResponse getAllUsers() {
        return GetAllResponse.builder().success(true).users(userRepository.findAll()).build();
    }
}
