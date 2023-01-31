package com.elixr.Poc2;

import com.elixr.poc.data.User;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.controller.UserModificationController;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserModificationControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserModificationController userModificationController;

    @Test
    public void test_ModifyUser_for_Success() {
        User expectedUser = User.builder().id(UUID.randomUUID()).userName("Tom Cruise").firstName("Tom").lastName("Cruise").build();
        UserRequest userRequest = new UserRequest();
        userRequest.setUserName(expectedUser.getUserName());
        userRequest.setFirstName(expectedUser.getFirstName());
        userRequest.setLastName(expectedUser.getLastName());
        Mockito.when(userService.updateUserPartially(expectedUser.getId().toString(), userRequest)).thenReturn(expectedUser);
        Assertions.assertEquals(new ResponseEntity<>(expectedUser, HttpStatus.OK), userModificationController.modifyUser(expectedUser.getId().toString(), userRequest));
    }

    public void test_ModifyUser_for_Failure() {
        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> userModificationController.modifyUser(null, null));
    }
}
