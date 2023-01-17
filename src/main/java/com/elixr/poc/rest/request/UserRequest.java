package com.elixr.poc.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * This Class holds the request attributes for user related end points
 */

@Data
public class UserRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
