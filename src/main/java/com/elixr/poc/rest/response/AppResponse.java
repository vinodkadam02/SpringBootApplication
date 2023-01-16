package com.elixr.poc.rest.response;

import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract class inherited by all response classes
 */
@Data
@NoArgsConstructor
public class AppResponse {
    private boolean success;
}
