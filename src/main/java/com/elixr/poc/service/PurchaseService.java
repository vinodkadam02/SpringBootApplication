package com.elixr.poc.service;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.repository.PurchaseRepository;
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

    public PurchaseResponse createPurchase(Purchase purchase) {
        savePurchase(purchase);
        return  PurchaseResponse.purchaseBuilder().success(true).id(purchase.getId()).userName(purchase.getUserName())
                .product(purchase.getProduct()).amount(purchase.getAmount()).date(purchase.getDate()).build();
    }

    /**
     * Calling purchase repository to store the valid user information to the database
     *
     * @param purchase
     * @return
     */
    private Purchase savePurchase(Purchase purchase) {
        if (purchase.getId() == null || purchase.getId().toString().isEmpty()) {
            purchase.setId(UUID.randomUUID());
        }
        purchase = purchaseRepository.save(purchase);
        return purchase;
    }
}
