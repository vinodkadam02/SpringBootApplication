package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * This Class holds the response attributes for purchase related end points.
 */

@Data
public class PurchaseResponse extends AppResponse {

    private UUID id;
    private String userName;
    private String product;
    private String amount;
    private String date;

    @Builder(builderMethodName = "purchaseBuilder")
    public PurchaseResponse(boolean success, List<String> errorMessage, UUID id, String userName, String product, String amount, String date) {
        super(success);
        this.id = id;
        this.userName = userName;
        this.product = product;
        this.amount = amount;
        this.date = date;
    }
}
