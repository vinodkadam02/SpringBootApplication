package com.elixr.poc.service;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.data.User;
import com.elixr.poc.exception.NoRecordFoundException;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.request.UserRequest;
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
    public boolean deleteUserDetails(UUID userId) throws NoRecordFoundException {
        boolean success = false;
        boolean userRecordExists = userRepository.existsById(userId);
        if (userRecordExists) {
            userRepository.deleteById(userId);
            success = true;
        } else {
            throw new NoRecordFoundException(ApplicationConstants.ID_MISMATCH);
        }
        return success;
    }

    /**
     * Creating a valid user
     * @param userRequestObject
     * @return
     */
    public UserResponse createValidUser(UserRequest userRequestObject) {
        User userObject = createUserObjectFromRequest(userRequestObject);
        saveDataToDatabase(userObject);
        return UserResponse.builder().success(true).id(userObject.getId()).userName(userObject.getUserName()).firstName(userObject.getFirstName()).lastName(userObject.getLastName()).build();
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
    public User getUserByUserId(UUID userId) throws NoRecordFoundException {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new NoRecordFoundException(ApplicationConstants.ID_MISMATCH));
    }
}
