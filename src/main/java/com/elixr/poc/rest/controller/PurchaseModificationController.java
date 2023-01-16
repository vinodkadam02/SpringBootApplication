package com.elixr.poc.rest.controller;

import com.elixr.poc.exception.IdNotFoundException;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@RequestMapping("/controller")
public class PurchaseModificationController {
    private final PurchaseService purchaseService;

    public PurchaseModificationController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * Calling updatePurchase method with the parameter purchaseId to update the purchase by purchaseId.
     * And handling the Exception if the userId is not matching.
     *
     * @param purchaseId
     * @return
     * @throws IdNotFoundException
     */
    @PatchMapping("/purchase/{purchaseId}")
    public ResponseEntity purchaseUpdate(@PathVariable("purchaseId") UUID purchaseId,
                                         @RequestBody @Valid PurchaseRequest purchaseDetails) throws IdNotFoundException {
        return new ResponseEntity<>(purchaseService.purchaseUpdate(purchaseId, purchaseDetails), HttpStatus.OK);
    }
}