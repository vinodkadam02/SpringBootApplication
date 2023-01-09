package com.elixr.poc.rest.response;

import com.elixr.poc.data.Purchase;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Send successful response to user while reterival operations.
 */
@Data
public class PurchaseGetResponse extends ErrorResponse {

    private List<Purchase> purchase;

    @Builder
    public PurchaseGetResponse(boolean success, List<Purchase> purchase) {
        setSuccess(success);
        this.purchase = purchase;
    }
}
