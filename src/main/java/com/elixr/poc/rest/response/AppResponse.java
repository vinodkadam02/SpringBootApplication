package com.elixr.poc.rest.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Abstract class inherited by all response classes
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse {
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errorMessage;
}
