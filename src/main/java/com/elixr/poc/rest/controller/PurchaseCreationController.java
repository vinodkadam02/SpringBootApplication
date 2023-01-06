package com.elixr.poc.rest.controller;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.rest.response.PurchaseResponse;
import com.elixr.poc.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller class for create purchase using post mapping
 */
@RestController
@Validated
@RequestMapping("/controller")
public class PurchaseCreationController {

    private final PurchaseService purchaseService;

    public PurchaseCreationController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> addPurchase(@RequestBody @Valid PurchaseRequest newPurchase) {
        Purchase purchase = Purchase.builder().userName(newPurchase.getUserName()).product(newPurchase.getProduct()).amount(newPurchase.getAmount()).date(newPurchase.getDate()).build();
        purchaseService.createPurchase(purchase);

        return new ResponseEntity<>(PurchaseResponse.purchaseBuilder().success(true).id(purchase.getId()).userName(purchase.getUserName()).product(purchase.getProduct()).amount(purchase.getAmount()).date(purchase.getDate()).build(), HttpStatus.OK);
    }
}
