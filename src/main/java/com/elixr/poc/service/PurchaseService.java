package com.elixr.poc.service;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.repository.PurchaseRepository;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.rest.response.PurchaseResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * service class for editing response and save or update new purchase to database
 */

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    private static Purchase createPurchaseObjectFromRequest(PurchaseRequest purchaseRequest) {
        return Purchase.builder().userName(purchaseRequest.getUserName()).product(purchaseRequest.getProduct()).amount(purchaseRequest.getAmount()).date(purchaseRequest.getDate()).build();
    }

    public PurchaseResponse createPurchase(PurchaseRequest purchaseRequestObject) {
        Purchase purchaseObject = createPurchaseObjectFromRequest(purchaseRequestObject);
        saveRepository(purchaseObject);
        return PurchaseResponse.purchaseBuilder().success(true).id(purchaseObject.getId()).userName(purchaseObject.getUserName()).product(purchaseObject.getProduct()).amount(purchaseObject.getAmount()).date(purchaseObject.getDate()).build();
    }

    /**
     * Calling purchase repository to store the valid user information to the database
     * @param purchase
     * @return
     */
    private Purchase saveRepository(Purchase purchase) {
        if (purchase.getId() == null || purchase.getId().toString().isEmpty()) {
            purchase.setId(UUID.randomUUID());
        }
        purchase = this.purchaseRepository.save(purchase);
        return purchase;
    }
}
