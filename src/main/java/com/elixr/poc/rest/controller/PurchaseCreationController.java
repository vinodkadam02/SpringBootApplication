package com.elixr.poc.rest.controller;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*controller class for create purchase using post mapping*/

@RestController
@RequestMapping("/controller")
public class PurchaseCreationController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/purchase")
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequest newPurchase) {
        PurchaseService newService = new PurchaseService();
        return newService.editResponse(newPurchase);
    }
}
