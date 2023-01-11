package com.elixr.poc.rest.controller;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.exception.IdNotFoundException;
import com.elixr.poc.rest.response.CommonResponse;
import com.elixr.poc.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/controller")
/**
 * purchaseDeletionController is invoking the purchaseService.
 */ public class PurchaseDeletionController {
    private final PurchaseService purchaseService;

    public PurchaseDeletionController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * Calling deletePurchaseDetails method with the parameter of the purchaseId to delete purchase and
     * handling the exception if Id is not found
     *
     * @param purchaseId
     * @return
     */
    @DeleteMapping("/purchase/{purchaseId}")
    public ResponseEntity deletePurchase(@PathVariable("purchaseId") UUID purchaseId) throws IdNotFoundException {
        CommonResponse deleteResponse;
        HttpStatus httpStatus;
        boolean success = purchaseService.deletePurchaseDetails(purchaseId);
        deleteResponse = CommonResponse.builder().success(success)
                .errorMessage(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DELETED_SUCCESSFULLY.getKey())).build();
        httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(deleteResponse, httpStatus);
    }
}
