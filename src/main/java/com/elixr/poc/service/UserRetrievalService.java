package com.elixr.poc.service;
import com.elixr.poc.data.User;
import com.elixr.poc.repository.GlobalRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserRetrievalService {

    private GlobalRepository userRepository;
    private ConversionService conversionService;

    public UserRetrievalService(GlobalRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    /**
     * Retrieving all users
     * @return
     */
    public List<User> getAllUsers(){
        List<User>  allUsers = userRepository.findAll();
        return allUsers;
    }
}
