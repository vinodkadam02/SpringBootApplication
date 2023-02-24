package com.elixr.poc.service;

import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.data.User;
import com.elixr.poc.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void test_GetAllUsers_For_Success() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().userName("Khal Drogo").firstName("Khal").lastName("Drogo").build());
        users.add(User.builder().userName("Jon Snow").firstName("Jon").lastName("Snow").build());
        org.assertj.core.api.Assertions.assertThat(users).size().isGreaterThan(0);
    }

    @Test
    void test_GetAllUsers_for_Failure() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().userName("Khal Drogo").firstName("Khal").lastName("Drogo").build());
        users.add(User.builder().userName("Jon Snow").firstName("Jon").lastName("Snow").build());
        Mockito.when(userRepository.findAll()).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class, () -> userService.getAllUsers());
    }

    @Test
    void test_GetUserByUserId_For_Success() {
        User expectedUser = User.builder().id(UUID.fromString("be8773c3-eb9b-4564-a570-b66d90005501"))
                .userName("Sansa Stark").firstName("Sansa").lastName("Stark").build();
        Mockito.when(userRepository
                .findById(any(UUID.class))).thenReturn(Optional.ofNullable(expectedUser));
        User actualUser = userService.getUserByUserId("be8773c3-eb9b-4564-a570-b66d90005502");
        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    void test_GetUserByUserId_For_Failure() {
        Mockito.when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userService.getUserByUserId("be8773c3-eb9b-4564-a570-b66d90005502"));
    }
}
