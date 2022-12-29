package com.elixr.poc.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Builder
public class User {

    @Id
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
}
