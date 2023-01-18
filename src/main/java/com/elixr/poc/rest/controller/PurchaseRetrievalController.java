package com.elixr.poc.rest.controller;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.data.User;
import com.elixr.poc.rest.response.PurchaseGetResponse;
import com.elixr.poc.rest.response.PurchaseResponse;
import com.elixr.poc.service.PurchaseService;
import com.elixr.poc.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for purchase details
 */

@RestController
public class PurchaseRetrievalController {
    /**
     * Get the purchase by userName
     */
    private final PurchaseService purchaseService;

    public PurchaseRetrievalController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase/{userName}")
    public ResponseEntity<PurchaseGetResponse> retrievePurchaseByUserName(@PathVariable("userName") String userName) {
        List<Purchase> purchases = purchaseService.getPurchaseByUserName(userName);
        return new ResponseEntity<>(PurchaseGetResponse.builder().success(true).purchases(purchases).build(), HttpStatus.OK);
    }

    /**
     * Getting the purchase by purchaseID
     * Calling a method getPurchaseByPurchaseID
     * Returning the response
     * And handling the exception if the purchaseId is not matching.
     */
    @GetMapping("/purchases/{purchaseId}")
    public ResponseEntity retrievePurchase(@PathVariable("purchaseId") @Valid String purchaseId) {
        HttpStatus httpStatus;
        Purchase purchase = purchaseService.getPurchaseByPurchaseId(purchaseId);
        PurchaseResponse purchaseResponse = PurchaseResponse.purchaseBuilder().success(true).id(purchase.getId())
                .userName(purchase.getUserName()).product(purchase.getProduct()).amount(purchase.getAmount())
                .date(purchase.getDate()).build();
        httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(purchaseResponse, httpStatus);
        /**
         * Getting the purchase by userId
         * Calling a method getPurchaseByUserID
         * Returning the response
         */
        private final PurchaseService purchaseService;
        private final UserService userService;

    public PurchaseRetrievalController(PurchaseService purchaseService, UserService userService) {
            this.purchaseService = purchaseService;
            this.userService = userService;
        }

        @GetMapping("/purchase/{userId}")
        public ResponseEntity<PurchaseGetResponse> retrievePurchase (@RequestBody @PathVariable("userId") String userId)
        {
            User user = userService.getUserByUserId(userId);
            return new ResponseEntity<>(purchaseService.getPurchaseByUserName(user.getUserName()), HttpStatus.OK);
        }
    }
}

