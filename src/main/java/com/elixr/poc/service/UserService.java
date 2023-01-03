package com.elixr.poc.service;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.response.DeleteResponse;
import org.springframework.stereotype.Service;

import java.util.*;

/*
Service layer
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private DeleteResponse deleteResponse;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    Deleting the user by the userId.
     */
    public DeleteResponse deleteUserDetails(UUID userId) {
        deleteResponse = new DeleteResponse();
        boolean userRecords = userRepository.existsById(userId);
        if (userRecords) {
            userRepository.deleteById(userId);
            deleteResponse.setSuccess(true);
            ApplicationConstants.REQUEST_VALUE = 200;
            deleteResponse.setMessage(ApplicationConstants.SUCCESSFULLY_DELETED);
            return deleteResponse;
        } else {
            deleteResponse.setSuccess(false);
            deleteResponse.setMessage(ApplicationConstants.ID_MISMATCH);
            ApplicationConstants.REQUEST_VALUE = 404;
            return deleteResponse;
        }
    }
}
