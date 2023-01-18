package com.elixr.poc.rest.response;
import lombok.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract class inherited by all user response classes
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse {
    private boolean success;
}
