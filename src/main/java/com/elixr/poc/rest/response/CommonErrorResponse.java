package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonErrorResponse {
    private boolean success;
    private String errorMessage;
}
