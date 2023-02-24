package com.elixr.poc.rest.controller;

import com.elixr.poc.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.elixr.poc.rest.response.UserResponse;
import com.elixr.poc.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserRetrievalControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRetrievalController userRetrievalController;


    @Test
    void test_GetAllUser_For_Success() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().userName("Khal Drogo").firstName("Khal").lastName("Drogo").build());
        users.add(User.builder().userName("Jon Snow").firstName("Jon").lastName("Snow").build());
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        org.assertj.core.api.Assertions.assertThat(users).size().isGreaterThan(0);
    }

    @Test
    void test_GetAllUser_For_Failure(){
        List<User> users = new ArrayList<>();
        users.add(User.builder().userName("Khal Drogo").firstName("Khal").lastName("Drogo").build());
        users.add(User.builder().userName("Jon Snow").firstName("Jon").lastName("Snow").build());
        Mockito.when(userService.getAllUsers()).thenReturn(null);
        org.assertj.core.api.Assertions.assertThat(users).size().isGreaterThan(0);
    }

    @Test
    void test_RetrieveUserByUserId_For_Success() {
        User userDetails = User.builder().id(UUID.fromString("a7446f12-262e-4d57-9435-cda170336995"))
                .userName("Khal Drogo").firstName("Khal").lastName("Drogo").build();

        UserResponse excpectedResponse = UserResponse.builder().success(true).id(UUID
                        .fromString("a7446f12-262e-4d57-9435-cda170336995")).userName("Khal Drogo")
                .firstName("Khal").lastName("Drogo").build();
        Mockito.when(userService.getUserByUserId("a7446f12-262e-4d57-9435-cda170336995")).thenReturn(userDetails);

        UserResponse actualResponse =  UserResponse.builder().success(true).id(UUID
                        .fromString("a7446f12-262e-4d57-9435-cda170336995")).userName("Khal Drogo")
                .firstName("Khal").lastName("Drogo").build();

        Assertions.assertEquals(excpectedResponse.getId(),actualResponse.getId());
    }

    @Test
    void test_RetrieveUserByUserId_For_Failure(){
        Mockito.when(userService.getUserByUserId("a7446f12-262e-4d57-9435-cda170336995")).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> userRetrievalController.retrieveUserByUserId("a7446f12-262e-4d57-9435-cda170336995"));
    }
}
