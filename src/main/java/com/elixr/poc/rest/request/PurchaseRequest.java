package com.elixr.poc.rest.request;
import com.elixr.poc.Constant.ErrorConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.UUID;

/**
 * This Class holds the request attributes for purchase related end points
 */

@Data
public class PurchaseRequest {
    @Id
    private UUID id;
    @NotBlank(message = ErrorConstants.USER_NAME_MISSING_IN_PURCHASE)
    private String userName;
    @NotBlank(message = ErrorConstants.PRODUCT_NAME_IS_MISSING_IN_PURCHASE)
    private String product;
    @NotBlank(message = ErrorConstants.AMOUNT_MISSING_IN_PURCHASE)
    private String amount;
    @NotBlank(message = ErrorConstants.DATE_MISSING_IN_PURCHASE)
    private String date;
}
