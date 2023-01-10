package com.elixr.poc.rest.response;

import lombok.*;


/**
 * Abstract class inherited by all response classes
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AppResponse {
    private boolean success;
}
