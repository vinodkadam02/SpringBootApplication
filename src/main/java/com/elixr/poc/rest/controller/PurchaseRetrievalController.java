package com.elixr.poc.rest.controller;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.exception.GlobalException;
import com.elixr.poc.rest.response.PurchaseResponse;
import com.elixr.poc.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/controller")
public class PurchaseRetrievalController {
    private final PurchaseService purchaseService;

    public PurchaseRetrievalController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase/{purchaseId}")
    public ResponseEntity retrievePurchase(@PathVariable("purchaseId") UUID purchaseId) throws GlobalException {
        HttpStatus httpStatus;
        try {
            Purchase purchase = purchaseService.getPurchaseByPurchaseId(purchaseId);
            PurchaseResponse purchaseResponse = PurchaseResponse.purchaseBuilder().success(true).id(purchase.getId()).userName(purchase.getUserName())
                    .product(purchase.getProduct()).amount(purchase.getAmount()).date(purchase.getDate()).build();
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(purchaseResponse, httpStatus);
        } catch (GlobalException globalException) {
            throw globalException;
        }
    }

}