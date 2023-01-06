package com.elixr.poc.rest.response;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;


/**
 * Abstract class inherited by all user response classes
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class UserResponse {
    protected boolean success;
}
