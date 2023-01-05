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

    public PurchaseResponse createPurchase(Purchase newPurchase) {
        savePurchase(newPurchase);
        return PurchaseResponse.purchaseBuilder().success(true).id(newPurchase.getId())
                .userName(newPurchase.getUserName()).product(newPurchase.getProduct())
                .amount(newPurchase.getAmount()).date(newPurchase.getDate()).build();
    }

    /**
     * Calling purchase repository to store the valid user information to the database
     * @param purchase
     * @return
     */
    private Purchase savePurchase(Purchase purchase) {
        if (purchase.getId() == null || purchase.getId().toString().isEmpty()) {
            purchase.setId(UUID.randomUUID());
        }
        purchase = this.purchaseRepository.save(purchase);
        return purchase;
    }
}
