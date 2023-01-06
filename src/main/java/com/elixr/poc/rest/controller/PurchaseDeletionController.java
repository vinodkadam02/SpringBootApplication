//package com.elixr.poc.rest.controller;
//
//import com.elixr.poc.constants.ApplicationConstants;
//import com.elixr.poc.exception.NoRecordFoundException;
//import com.elixr.poc.rest.response.DeleteResponse;
//import com.elixr.poc.service.PurchaseService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/controller")
///**
// * purchaseDeletionController is invoking the purchaseService.
// */ public class PurchaseDeletionController {
//    private final PurchaseService purchaseService;
//
//    public PurchaseDeletionController(PurchaseService purchaseService) {
//        this.purchaseService = purchaseService;
//    }
//
//    /**
//     * Calling deletePurchaseDetails method with the parameter of the purchaseId to delete purchase and
//     * handling the exception if Id is not found
//     *
//     * @param purchaseId
//     * @return
//     */
//    @DeleteMapping("/purchase/{purchaseId}")
//    public ResponseEntity<?> deletePurchase(@PathVariable("purchaseId") UUID purchaseId) {
//        DeleteResponse deleteResponse = new DeleteResponse();
//        HttpStatus httpStatus;
//        try {
//            boolean success = purchaseService.deletePurchaseDetails(purchaseId);
//            deleteResponse.setSuccess(success);
//            deleteResponse.setErrorMessages(ApplicationConstants.DELETED);
//            httpStatus = HttpStatus.OK;
//        } catch (NoRecordFoundException e) {
//            deleteResponse.setSuccess(false);
//            deleteResponse.setErrorMessages(e.getMessage());
//            httpStatus = HttpStatus.BAD_REQUEST;
//        }
//        return new ResponseEntity<>(deleteResponse, httpStatus);
//    }
//}
