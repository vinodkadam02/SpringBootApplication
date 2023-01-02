package com.elixr.poc.service;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.repository.PurchaseRepository;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.rest.response.PurchaseErrorResponse;
import com.elixr.poc.rest.response.PurchaseResponse;
import com.elixr.poc.rest.validator.PurchaseValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/* service class for editing response and save or update new purchase to database  */
@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    private static Purchase createUserObjectFromRequest(PurchaseRequest purchaseRequest) {
        return Purchase.builder().userName(purchaseRequest.getUserName()).product(purchaseRequest.getProduct()).amount(purchaseRequest.getAmount()).date(purchaseRequest.getDate()).build();
    }

    public ResponseEntity<?> createPurchase(PurchaseRequest newPurchase) {
        PurchaseResponse newPurchaseResponse;
        Purchase newPurchaseFormat = createUserObjectFromRequest(newPurchase);
        PurchaseErrorResponse newPurchaseErrorResponse;
        PurchaseValidator purchaseValidator = new PurchaseValidator();
        List<String> errorList = purchaseValidator.validate(newPurchase);
        HttpStatus httpStatusCode = HttpStatus.OK;
        if (!errorList.isEmpty()) {
            newPurchaseErrorResponse = PurchaseErrorResponse.builder().success(false).errorMsg(errorList).build();
            httpStatusCode = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(newPurchaseErrorResponse, httpStatusCode);
        }
        newPurchaseResponse = PurchaseResponse.builder().success(true).id(UUID.randomUUID()).userName(newPurchaseFormat.getUserName()).product(newPurchaseFormat.getProduct()).amount(newPurchaseFormat.getAmount()).date(newPurchaseFormat.getDate()).build();
        saveOrUpdate(newPurchaseFormat);
        return new ResponseEntity<>(newPurchaseResponse, httpStatusCode);
    }


    /* to do
    calling the repository to persist the purchase object in db that is in scope of another jira INTTNG-202
    so this method will be refactored as part of that jira ticket.
     */
    public Purchase saveOrUpdate(Purchase purchase) {
        if (purchase.getId() == null || purchase.getId().toString().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            purchase.setId(uuid);
        }
        purchase = this.purchaseRepository.save(purchase);
        return purchase;

    }
}
