package com.elixr.poc.service;

import com.elixr.poc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/*
Service layer
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    Deleting the user by the userId.
     */
    public boolean deleteUserDetails(UUID userId) {
        boolean success = false;
        try {
            userRepository.deleteById(userId);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
