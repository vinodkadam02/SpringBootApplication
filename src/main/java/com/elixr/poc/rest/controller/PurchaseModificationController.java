package com.elixr.poc.rest.controller;

import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
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
     * @throws NotFoundException
     */
    @PatchMapping("/purchase/{purchaseId}")
    public ResponseEntity purchaseUpdate(@PathVariable("purchaseId") String purchaseId, @RequestBody
    @Valid PurchaseRequest purchaseDetails) {
        return new ResponseEntity<>(purchaseService.purchaseUpdate(purchaseId, purchaseDetails), HttpStatus.OK);
    }
}
