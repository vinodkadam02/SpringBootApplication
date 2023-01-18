package com.elixr.poc.rest.controller;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.rest.response.PurchaseResponse;
import com.elixr.poc.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/purchase/{purchaseId}")
    public ResponseEntity retrievePurchase(@PathVariable("purchaseId") @Valid String purchaseId) {
        HttpStatus httpStatus;
            Purchase purchase = purchaseService.getPurchaseByPurchaseId(purchaseId);
            PurchaseResponse purchaseResponse = PurchaseResponse.purchaseBuilder().success(true).id(purchase.getId())
                    .userName(purchase.getUserName()).product(purchase.getProduct()).amount(purchase.getAmount())
                    .date(purchase.getDate()).build();
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(purchaseResponse, httpStatus);
    }
}
