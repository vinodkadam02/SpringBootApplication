package com.elixr.poc.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;


/**
 * Abstract class inherited by all user response classes
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class ErrorResponse {
    protected boolean success;
}
