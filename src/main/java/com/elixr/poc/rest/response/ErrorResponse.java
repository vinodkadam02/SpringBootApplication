package com.elixr.poc.rest.response;
import lombok.*;

import java.util.List;

/**
 * Abstract class inherited by all purchase response classes
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ErrorResponse {
    private boolean success;
}
