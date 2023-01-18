package com.elixr.poc.rest.controller;

import com.elixr.poc.rest.response.PurchaseGetResponse;
import com.elixr.poc.service.PurchaseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for purchase details.
 */

@Validated
@RestController
@RequestMapping("/controller")
public class PurchaseRetrievalController {
    /**
     * Getting the purchase by purchaseID
     * Calling a method getPurchaseByPurchaseID
     * Returning the response
     * And handling the exception if the purchaseId is not matching.
     */

    private final PurchaseService purchaseService;

    public PurchaseRetrievalController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase")
    public PurchaseGetResponse getAllPurchase() {
        return purchaseService.getAllPurchases();
    }
}
