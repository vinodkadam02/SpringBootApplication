package com.elixr.poc.service;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.data.User;
import com.elixr.poc.exception.GlobalException;
import com.elixr.poc.exception.NoRecordFoundException;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.rest.response.CommonErrorResponse;
import com.elixr.poc.rest.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service class to interact with controller and create new user
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Deleting the user by the userId.
     * Throwing a NoRecordFoundException to handel if the UserId is not present.
     */
    public boolean deleteUserDetails(UUID userId) throws GlobalException {
        boolean success = false;
        boolean userRecordExists = userRepository.existsById(userId);
        if (userRecordExists) {
            userRepository.deleteById(userId);
            success = true;
        } else {
            throw new GlobalException(ApplicationConstants.ID_MISMATCH);
        }
        return success;
    }

    /**
     * Creating a valid user
     * @param user
     * @return
     */
    public UserResponse createValidUser(User user) {
        saveDataToDatabase(user);
        return UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName()).firstName(user.getFirstName()).lastName(user.getLastName()).build();
    }

    /**
     * Calling the repository to store data
     * @param user
     * @return
     */
    private User saveDataToDatabase(User user) {

        if (user.getId() == null || user.getId().toString().isEmpty()) {
            user.setId(UUID.randomUUID());
        }
        user = this.userRepository.save(user);
        return user;
    }

    /**
     * Finding User by userId and returning the user.
     * @param userId
     * @return
     * @throws NoRecordFoundException
     */
    public User getUserByUserId(UUID userId) throws  GlobalException {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new GlobalException(ApplicationConstants.ID_MISMATCH));
    }
}
