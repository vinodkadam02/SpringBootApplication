package com.elixr.poc.rest.response;

import com.elixr.poc.data.Purchase;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseGetResponse extends AppResponse {
    private List<Purchase> purchases;

    @Builder
    public PurchaseGetResponse(boolean success, List<Purchase> purchases) {
        setSuccess(success);
        this.purchases = purchases;
    }
}
