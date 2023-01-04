package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 *  This Class holds the response attributes for user related end points
 */

@Data
@Builder
public class UserPostResponse {

    private boolean success;
    @Id
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;

}
