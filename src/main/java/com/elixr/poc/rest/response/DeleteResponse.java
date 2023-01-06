package com.elixr.poc.rest.response;

import com.elixr.poc.data.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DeleteResponse extends ErrorResponse{

    private String errorMessage;
    @Builder
    public DeleteResponse(String errorMessage) {
        setSuccess(true);
        this.errorMessage = errorMessage;
    }
}
