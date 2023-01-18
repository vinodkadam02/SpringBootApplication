package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CommonResponse extends AppResponse{
    private String errorMessage;

    @Builder
    public CommonResponse(boolean success,String errorMessage){
        setSuccess(success);
        this.errorMessage = errorMessage;
    }
}
