package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * This Class holds the response attributes for user related end points
 */

@Data
public class UserResponse extends ErrorResponse {

    @Id
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;

    @Builder
    public UserResponse(boolean success, UUID id, String userName, String firstName, String lastName) {
        setSuccess(success);
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
