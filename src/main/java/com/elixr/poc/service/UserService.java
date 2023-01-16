package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdFormatException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.User;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.response.AppResponse;
import com.elixr.poc.rest.response.PostErrorResponse;
import com.elixr.poc.rest.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
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
            throw new IdFormatException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_INVALID_ID_FORMAT.getKey()));
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
            throw new NotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXISTS.getKey(), "User"));
        }
        return true;
    }

    /**
     * Creating a valid new user.
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
     *
     * @param userId
     * @return
     * @throws NotFoundException
     */
    public User getUserByUserId(String userId) {
        UUID uuid = uuidValidation(userId);
        Optional<User> user = userRepository.findById(uuid);
        return user.orElseThrow(() -> new NotFoundException(MessagesUtil
                .getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXISTS.getKey(), "User")));
    }

    /**
     * Retrieve user by username.
     * @param userName
     * @return
     */
    public AppResponse getUserByName(String userName) {
        if (!userRepository.existsByUserName(userName)) {
           throw new NotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXISTS.getKey(),"User"));
        }
        User existingUser = userRepository.findByUserName(userName);

        return UserResponse.builder().success(true).id(existingUser.getId()).userName(existingUser.getUserName())
                .firstName(existingUser.getFirstName())
                .lastName(existingUser.getLastName()).build();
    }
}
