package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;


@Data
public class CommonErrorResponse extends AppResponse {
    private String errorMessage;

    @Builder
    public CommonErrorResponse(boolean success, String errorMessage) {
        setSuccess(success);
        this.errorMessage = errorMessage;
    }
}
