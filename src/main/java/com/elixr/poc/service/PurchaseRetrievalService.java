package com.elixr.poc.service;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.repository.PurchaseRepository;
import org.springframework.core.convert.ConversionService;

import java.util.List;

public class PurchaseRetrievalService {

    private PurchaseRepository purchaseRepository;
    private ConversionService conversionService;

    public PurchaseRetrievalService(PurchaseRepository purchaseRepository,ConversionService conversionService)
    {
        this.purchaseRepository = purchaseRepository;
        this.conversionService = conversionService;
    }

    /**
     * Retrieving all purchase
     */

    public List<Purchase> getAllPurchase(){
        List<Purchase> allPurchase = purchaseRepository.findAll();
        return allPurchase;
    }
}
