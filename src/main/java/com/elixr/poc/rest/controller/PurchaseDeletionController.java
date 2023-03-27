package com.elixr.poc.rest.controller;

import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.rest.response.SuccessResponse;
import com.elixr.poc.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * purchaseDeletionController is invoking the purchaseService.
 */
public class PurchaseDeletionController {

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
    public ResponseEntity<SuccessResponse> deletePurchase(@PathVariable("purchaseId") @Valid String purchaseId) {
        boolean success = purchaseService.deletePurchaseDetails(purchaseId);
        SuccessResponse deleteSuccessResponse = SuccessResponse.builder().success(success)
                .successMessage(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DELETED_SUCCESSFULLY.getKey())).build();
        return new ResponseEntity<>(deleteSuccessResponse, HttpStatus.OK);
    }
}
