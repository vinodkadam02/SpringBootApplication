package com.elixr.poc.rest.response;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
public class PurchaseErrorResponse {
    private boolean success;
    private List<String> errorMessage;

}
