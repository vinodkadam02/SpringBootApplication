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
    public int REQUEST_VALUE;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    Deleting the user by the userId.
     */
    public DeleteResponse deleteUserDetails(UUID userId) {
        DeleteResponse deleteResponse = new DeleteResponse();
        boolean userRecordExists = userRepository.existsById(userId);
        if (userRecordExists) {
            userRepository.deleteById(userId);
            REQUEST_VALUE = 200;
            deleteResponse.setSuccess(true);
            deleteResponse.setMessage(ApplicationConstants.SUCCESSFULLY_DELETED);
        } else {
            REQUEST_VALUE = 404;
            deleteResponse.setSuccess(false);
            deleteResponse.setMessage(ApplicationConstants.ID_MISMATCH);
        }
        return deleteResponse;
    }
}
