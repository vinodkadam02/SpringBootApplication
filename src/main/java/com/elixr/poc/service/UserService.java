package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdFormatException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.User;
import com.elixr.poc.common.exception.IdNotFoundException;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.response.GetAllResponse;
import com.elixr.poc.rest.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
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
     * @throws IdNotFoundException
     */
    public boolean deleteUserDetails(String userId) {
        UUID uuid = uuidValidation(userId);
        boolean userRecordExists = userRepository.existsById(uuid);
        if (!userRecordExists) {
            throw new IdNotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey(), "User"));
        }
        userRepository.deleteById(uuid);
        return true;
    }

    /**
     * Creating a valid user
     *
     * @param user
     * @return
     */
    public UserResponse createValidUser(User user) {
        saveUser(user);
        return UserResponse.builder().success(true).id(user.getId()).userName(user.getUserName()).firstName(user
                .getFirstName()).lastName(user.getLastName()).build();
    }

    /**
     * Calling the repository to store data
     *
     * @param user
     * @return
     */
    private void saveUser(User user) {

        if (user.getId() == null || user.getId().toString().isEmpty()) {
            user.setId(UUID.randomUUID());
        }
        this.userRepository.save(user);
    }

    /**
     * Retriving all the users
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
     * @throws IdNotFoundException
     */
    public User getUserByUserId(String userId) {
        UUID uuid = uuidValidation(userId);
        Optional<User> user = userRepository.findById(uuid);
        return user.orElseThrow(() -> new IdNotFoundException(MessagesUtil
                .getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey(), "User")));
    }
}
