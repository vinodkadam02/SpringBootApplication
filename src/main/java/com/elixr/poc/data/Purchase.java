package com.elixr.poc.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "purchaseDetails")
public class Purchase {
    @Id
    private UUID id;
    private String userName;
    private UUID userId;
    private String product;
    private String amount;
    private String date;
}
