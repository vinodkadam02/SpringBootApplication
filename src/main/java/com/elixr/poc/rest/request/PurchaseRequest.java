package com.elixr.poc.rest.request;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.UUID;
/* This Class holds the request attributes for purchase related end points */

@Data
public class PurchaseRequest {
    @Id
    private UUID id;
    private String userName;
    private String product;
    private String amount;
    private String date;
}
