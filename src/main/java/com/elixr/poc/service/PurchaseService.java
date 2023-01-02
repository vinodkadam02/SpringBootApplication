package com.elixr.poc.service;

import com.elixr.poc.Repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    /*
    Deleting the purchase from the database
     */
    public boolean deletePurchaseDetails(UUID purchaseId) {
        boolean success = false;
        try {
            purchaseRepository.deleteById(purchaseId);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
