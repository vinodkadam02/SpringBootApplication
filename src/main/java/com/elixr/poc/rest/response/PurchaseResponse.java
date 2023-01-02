package com.elixr.poc.rest.response;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.UUID;
/* This Class holds the response attributes for purchase related end points */
@Data
@Builder
public class PurchaseResponse {
    private boolean success;
    @Id
    private UUID id;
    private String userName;
    private String product;
    private String amount;
    private String date;
}
