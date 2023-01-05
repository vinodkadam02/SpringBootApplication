package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * This Class holds the response attributes for user related end points
 */

@Data
public class UserPostResponse extends UserResponse {

    @Id
    private String id;
    private String userName;
    private String firstName;
    private String lastName;

    @Builder(builderMethodName = "newBuilder")
    public UserPostResponse( String id, String userName, String firstName, String lastName) {
        setSuccess(true);
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
