package com.elixr.poc.service;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.repository.PurchaseRepository;
import org.springframework.core.convert.ConversionService;

import java.util.List;

public class PurchaseRetrievalService {

    private PurchaseRepository purchaseRepository;

    public PurchaseRetrievalService(PurchaseRepository purchaseRepository)
    {
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * Retrieving all purchase
     */

    public List<Purchase> getAllPurchase(){
        List<Purchase> allPurchase = purchaseRepository.findAll();
        return allPurchase;
    }
}
