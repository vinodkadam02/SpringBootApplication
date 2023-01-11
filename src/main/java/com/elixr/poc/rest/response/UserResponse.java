package com.elixr.poc.rest.response;
import com.elixr.poc.data.Purchase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class UserResponse extends AppResponse {

    @Id
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    @JsonIgnoreProperties(value = "null")
    private List<Purchase> purchases;

    @Builder
    public UserResponse(boolean success,UUID id, String userName, String firstName, String lastName) {
        setSuccess(success);
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }}