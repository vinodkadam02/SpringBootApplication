package com.elixr.Poc2.service;

import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.data.User;
import com.elixr.poc.repository.UserRepository;
import com.elixr.poc.rest.request.UserRequest;
import com.elixr.poc.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private  UserRepository userRepository;
    @InjectMocks
    private  UserService userService;

    @Test
    public void test_UpdateUserPartially_For_Success() {
        User expectedUser = User.builder().id(UUID.fromString("a82f79b4-92a7-4748-a13b-9a1977468f3e"))
                .userName("Harish Surf").firstName("Harish").lastName("Surf").build();
        Mockito.when(userRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(expectedUser));
        Mockito.when(userRepository.save(any(User.class))).thenReturn(expectedUser);
        UserRequest userRequest = new UserRequest();
        userRequest.setUserName(expectedUser.getUserName());
        userRequest.setFirstName(expectedUser.getFirstName());
        userRequest.setLastName(expectedUser.getLastName());
        User actualUser = userService.updateUserPartially("a82f79b4-92a7-4748-a13b-9a1977468f3e", userRequest);
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser.getUserName(), actualUser.getUserName());
    }

    @Test
    public void test_UpdateUserPartially_For_Failure(){
        Assertions.assertThrows(NotFoundException.class, () -> userService.updateUserPartially("a82f79b4-92a7-4748-a13b-9a1977468f3e",null));
    }
}
