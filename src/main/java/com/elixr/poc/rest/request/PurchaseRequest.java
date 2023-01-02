package com.elixr.poc.rest.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.UUID;
/* This Class holds the request attributes for purchase related end points */

@Data
public class PurchaseRequest {
    @Id
    private UUID id;
    @NotBlank
    private String userName;
    @NotBlank
    private String product;
    @NotBlank
    private String amount;
    @NotBlank
    private String date;
}
