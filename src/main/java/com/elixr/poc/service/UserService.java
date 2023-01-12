package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdFormatException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.User;
import com.elixr.poc.common.exception.IdNotFoundException;
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
     * @param userId
     * @return
     * @throws IdNotFoundException
     */
    public boolean deleteUserDetails(String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            boolean success = false;
            boolean userRecordExists = userRepository.existsById(uuid);
            if (userRecordExists) {
                userRepository.deleteById(uuid);
                success = true;
            } else {
                throw new IdNotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey()));
            }
            return success;
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IdFormatException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_INVALID_ID_FORMAT.getKey()));
        }
    }

    /**
     * Creating a valid user
     * @param user
     * @return
     */
    public UserResponse createValidUser(User user) {
        saveDataToDatabase(user);
        return UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName()).firstName(user
                .getFirstName()).lastName(user.getLastName()).build();
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
     * @throws IdNotFoundException
     */
    public User getUserByUserId(String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            Optional<User> user = userRepository.findById(uuid);
            return user.orElseThrow(() -> new IdNotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey())));
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IdFormatException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_INVALID_ID_FORMAT.getKey()));
        }
    }

    /**
     * Updating the user details by userId.
     * @param userId
     * @param userDetails
     * @return
     * @throws IdNotFoundException
     */
    public User updateUserPartially(UUID userId, UserRequest userDetails) throws IdNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new IdNotFoundException("User not found on :: " + userId));
        user.setUserName(userDetails.getUserName());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        final User updatedUser = userRepository.save(user);
        return updatedUser;
    }
}
