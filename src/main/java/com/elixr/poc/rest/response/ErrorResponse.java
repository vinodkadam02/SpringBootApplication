package com.elixr.poc.rest.response;

import lombok.AllArgsConstructor;;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private boolean success;
}
