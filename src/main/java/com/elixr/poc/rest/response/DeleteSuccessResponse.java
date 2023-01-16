package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;

@Data
public class DeleteSuccessResponse extends AppResponse {
    private String successMessage;

    @Builder
    public DeleteSuccessResponse(boolean success, String successMessage) {
        setSuccess(success);
        this.successMessage = successMessage;
    }
}
