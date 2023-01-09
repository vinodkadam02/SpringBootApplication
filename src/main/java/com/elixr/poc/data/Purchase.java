package com.elixr.poc.data;

import com.elixr.poc.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document(collection = "purchaseDetails")
public class Purchase  {
    @Id
    private UUID id;
    @NotBlank(message = ApplicationConstants.USER_NAME_MISSING)
    private String userName;
    @NotBlank(message =ApplicationConstants.PRODUCT_NAME_IS_MISSING_IN_PURCHASE)
    private String product;
    @NotBlank(message = ApplicationConstants.AMOUNT_MISSING_IN_PURCHASE)
    private String amount;
    @NotBlank(message = ApplicationConstants.DATE_MISSING_IN_PURCHASE)
    private String date;
}
