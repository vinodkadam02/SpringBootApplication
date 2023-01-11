package com.elixr.poc.rest.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PurchaseRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String product;
    @NotBlank
    private String amount;
    @NotBlank
    private String date;
}
