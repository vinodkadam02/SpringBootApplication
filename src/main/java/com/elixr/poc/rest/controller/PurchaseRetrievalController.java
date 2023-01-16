package com.elixr.poc.rest.controller;


import com.elixr.poc.data.User;
import com.elixr.poc.rest.response.PurchaseGetResponse;
import com.elixr.poc.service.PurchaseService;
import com.elixr.poc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for purchase details.
 */
@RestController
@RequestMapping(path = "/controller")
public class PurchaseRetrievalController {
    /**
     * Getting the purchase by purchaseID
     * Calling a method getPurchaseByPurchaseID
     * Returning the response
     * And handling the exception if the purchaseId is not matching.
     */
    private final PurchaseService purchaseService;
    private final UserService userService;

    public PurchaseRetrievalController(PurchaseService purchaseService, UserService userService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    @GetMapping("/purchase/{userId}")
    public ResponseEntity<PurchaseGetResponse> retrievePurchase(@RequestBody @PathVariable("userId") String userId) {
        User user = userService.getUserByUserId(userId);
        return new ResponseEntity<>(purchaseService.getPurchaseByUserName(user.getUserName()), HttpStatus.OK);
    }
}
