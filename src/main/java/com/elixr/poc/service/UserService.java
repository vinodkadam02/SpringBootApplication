package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdFormatException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.User;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.rest.response.AppResponse;
import com.elixr.poc.rest.response.PostErrorResponse;
import com.elixr.poc.rest.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Service class to interact with controller and create new user
 */
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    /**
     * Validating UUID format.
     *
     * @param userId
     * @return
     */
    private UUID uuidValidation(String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            return uuid;
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IdFormatException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_INVALID_ID_FORMAT.getKey(),
                    MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_ID.getKey())));
        }
    }

    /**
     * Deleting the user by the userId.
     * Throwing a NoRecordFoundException to handel if the UserId is not present.
     *
     * @param userId
     * @return
     * @throws NotFoundException
     */
    public boolean deleteUserDetails(String userId) {
        UUID uuid = uuidValidation(userId);
        boolean userRecordExists = userRepository.existsById(uuid);
        if (!userRecordExists) {
            throw new NotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXIST.getKey(),
                    MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_ID.getKey())));
        }
        userRepository.deleteById(uuid);
        return true;
    }

    /**
     * Creating a valid new user.
     *
     * @param user
     * @return
     */
    public User createValidUser(User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            return null;
        } else {
            return saveUser(user);
        }
    }

    /**
     * Calling the repository to store data
     *
     * @param user
     * @return
     */
    private User saveUser(User user) {

        if (user.getId() == null || user.getId().toString().isEmpty()) {
            user.setId(UUID.randomUUID());
        }
       return  userRepository.save(user);
    }

    /**
     * Retrieving all the users
     *
     * @return
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Finding User by userId and returning the user.
     *
     * @param userId
     * @return
     * @throws NotFoundException
     */
    public User getUserByUserId(String userId) {
        UUID uuid = uuidValidation(userId);
        User user = userRepository.findById(uuid).orElseThrow(() -> new NotFoundException(MessagesUtil
                .getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXIST.getKey(),
                        MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_ID.getKey()))));
        return user;
    }

    /**
     * Retrieve user by username.
     *
     * @param userName
     * @return
     */
    public User getUserByName(String userName) {
        if (!userRepository.existsByUserName(userName)) {
            throw new NotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXIST.getKey(), "User"));
        }
        User existingUser = userRepository.findByUserName(userName);
        return existingUser;
    }

    /**
     * Updating the user details by userId.
     *
     * @param userId
     * @param userDetails
     * @return
     * @throws NotFoundException
     */
    public User updateUserPartially(String userId, UserRequest userDetails) {
        UUID uuid = uuidValidation(userId);
        User user = userRepository.findById(uuid).orElseThrow(() -> new NotFoundException(MessagesUtil
                .getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXIST.getKey(),
                        MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_ID.getKey()))));
        user.setUserName(userDetails.getUserName());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }
}
