package com.elixr.poc.rest.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
