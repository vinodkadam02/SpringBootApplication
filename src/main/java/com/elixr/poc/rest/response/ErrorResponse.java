package com.elixr.poc.rest.response;
import lombok.Data;
import java.util.List;

@Data
public class ErrorResponse {

    private boolean success;
    private List<String> errorMessage;

}
