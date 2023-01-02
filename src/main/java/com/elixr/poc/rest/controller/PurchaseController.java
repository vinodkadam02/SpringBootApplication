package com.elixr.poc.rest.controller;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*controller class for create purchase using post mapping*/

@RestController
@Validated
@RequestMapping("/controller")
public class PurchaseController {

    private final PurchaseService purchaseService;
    public PurchaseController(PurchaseService purchaseService)
    {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> addPurchase(@RequestBody @Valid PurchaseRequest newPurchase) {

        return purchaseService.createPurchase(newPurchase);
    }
}
