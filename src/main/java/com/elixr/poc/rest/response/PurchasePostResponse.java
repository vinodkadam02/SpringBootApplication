package com.elixr.poc.rest.response;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.UUID;

/**
 * This Class holds the response attributes for purchase related end points
 */
@Builder
@Data
public class PurchasePostResponse extends ErrorResponse {
    private boolean success;
    @Id
    private UUID id;
    private String userName;
    private String product;
    private String amount;
    private String date;
}
