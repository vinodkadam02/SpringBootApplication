package com.elixr.poc.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * This Class holds the request attributes for user related end points
 */

@Data
public class UserRequest {
    @Id
    private UUID id;
    @NotBlank
    private String userName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
