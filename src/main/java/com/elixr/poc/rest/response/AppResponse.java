package com.elixr.poc.rest.response;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract class inherited by all response classes
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse {
    private boolean success;
}
