package com.elixr.poc.rest.request;

import com.elixr.poc.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PurchaseRequest {
    @NotBlank(message = ApplicationConstants.USER_NAME_MISSING)
    private String userName;
    @NotBlank(message =ApplicationConstants.PRODUCT_NAME_IS_MISSING_IN_PURCHASE)
    private String product;
    @NotBlank(message = ApplicationConstants.AMOUNT_MISSING_IN_PURCHASE)
    private String amount;
    @NotBlank(message = ApplicationConstants.DATE_MISSING_IN_PURCHASE)
    private String date;
}
