package com.elixr.poc.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

/**
 * Entity class whose object is getting stored into db
 */
@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
}
