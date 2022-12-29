package com.elixr.poc.rest.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;
/* This Class holds the request attributes for user related end points */

@Data

public class UserRequest {
    @Id
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
}
