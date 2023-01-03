package com.elixr.poc.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document(collection = "user")
public class User {

    @Id
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;


}
