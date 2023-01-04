package com.elixr.poc.rest.controller;
import com.elixr.poc.rest.request.PurchaseRequest;
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
public class PurchasePostController {

    private final PurchaseService purchaseService;
    private PurchaseRequest purchaseRequest;

    public PurchasePostController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> addPurchase(@RequestBody @Valid PurchaseRequest newPurchase) {
        return new ResponseEntity<>(purchaseService.createValidPurchase(newPurchase), HttpStatus.OK);
    }
}
