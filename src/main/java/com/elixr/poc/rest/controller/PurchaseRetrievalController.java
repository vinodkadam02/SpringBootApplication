package com.elixr.poc.rest.controller;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.rest.response.PurchaseGetResponse;
import com.elixr.poc.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for purchase details.
 */

@RestController
@RequestMapping(path = "/controller")
public class PurchaseRetrievalController {
    /**
     * Get the purchase by userName
     */
    private final PurchaseService purchaseService;

    public PurchaseRetrievalController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase/{userName}")
    public ResponseEntity<PurchaseGetResponse> retrievePurchase(@RequestBody @PathVariable("userName") String userName) {
        List<Purchase> purchases = purchaseService.getPurchaseByUserName(userName);
        return new ResponseEntity<>(PurchaseGetResponse.builder().success(true).purchases(purchases).build(), HttpStatus.OK);
    }
}
