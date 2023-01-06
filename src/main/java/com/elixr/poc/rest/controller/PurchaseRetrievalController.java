package com.elixr.poc.rest.controller;

import com.elixr.poc.rest.response.PurchaseGetResponse;
import com.elixr.poc.service.PurchaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class PurchaseRetrievalController {
    private final PurchaseService purchaseService;

    public PurchaseRetrievalController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/getPurchase")
    public PurchaseGetResponse getAllPurchase() {
        return purchaseService.getAllPurchases();
    }
}