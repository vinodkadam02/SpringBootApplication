package com.elixr.poc.rest.response;
import lombok.*;

/**
 * Abstract class inherited by all purchase response classes
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AppResponse {
    private boolean success;
}
