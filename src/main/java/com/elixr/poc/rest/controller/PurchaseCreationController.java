package com.elixr.poc.rest.controller;

import com.elixr.poc.common.util.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.data.User;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.rest.response.CommonResponse;
import com.elixr.poc.service.PurchaseService;
import com.elixr.poc.service.UserService;
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
public class PurchaseCreationController {
    private final PurchaseService purchaseService;
    private final UserService userService;

    public PurchaseCreationController(PurchaseService purchaseService, UserService userService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    @PostMapping("/purchase")
    public ResponseEntity addPurchase(@RequestBody @Valid PurchaseRequest newPurchase) {
        User user = userService.getUserByName(newPurchase.getUserName());
        if (user != null) {
            Purchase purchase = Purchase.builder().userName(user.getUserName())
                    .product(newPurchase.getProduct()).amount(newPurchase.getAmount()).date(newPurchase.getDate()).build();
            return new ResponseEntity<>(purchaseService.createPurchase(purchase), HttpStatus.OK);
        } else {
            return new ResponseEntity(CommonResponse.builder().success(false)
                    .errorMessage(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_NOT_EXISTS.getKey())).build(), HttpStatus.NOT_FOUND);
        }
    }
}
