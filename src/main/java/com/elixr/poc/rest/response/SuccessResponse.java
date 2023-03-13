package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SuccessResponse extends AppResponse {
    private String successMessage;

    @Builder
    public SuccessResponse(boolean success, String successMessage) {
        setSuccess(success);
        this.successMessage = successMessage;
    }
}
