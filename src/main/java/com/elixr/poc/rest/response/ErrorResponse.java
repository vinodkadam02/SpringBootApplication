package com.elixr.poc.rest.response;

import lombok.*;


/**
 * Abstract class inherited by all user response classes.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class ErrorResponse {
    protected boolean success;
}
