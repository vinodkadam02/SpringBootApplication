package com.elixr.poc.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract class inherited by all response classes
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse {
    private boolean success;
}
