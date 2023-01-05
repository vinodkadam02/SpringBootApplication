package com.elixr.poc.rest.controller;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.exception.NoRecordFoundException;
import com.elixr.poc.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
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
     * @param fields
     * @return
     * @throws NoRecordFoundException
     */
    @PatchMapping("/purchase/{purchaseId}")
    public Purchase updatePurchase(@PathVariable("purchaseId") @Valid UUID purchaseId, @RequestBody Map<String, String> fields) throws NoRecordFoundException {
        return purchaseService.updatePurchase(purchaseId, fields);
    }
}
