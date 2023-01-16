package com.elixr.poc.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
