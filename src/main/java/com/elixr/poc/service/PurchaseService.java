package com.elixr.poc.service;

import com.elixr.poc.Constant.Constants;
import com.elixr.poc.Repository.PurchaseRepository;
import com.elixr.poc.rest.response.DeleteResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private DeleteResponse deleteResponse;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public DeleteResponse deletePurchaseDetails(UUID purchaseId) {
        deleteResponse = new DeleteResponse();
        boolean purchaseRecord = purchaseRepository.existsById(purchaseId);
        if (purchaseRecord) {
            purchaseRepository.deleteById(purchaseId);
            deleteResponse.setSuccess(true);
            Constants.REQUEST_VALUE = 200;
            deleteResponse.setMessages(Constants.DELETED);
            return deleteResponse;
        } else {
            deleteResponse.setSuccess(false);
            Constants.REQUEST_VALUE = 404;
            deleteResponse.setMessages(Constants.ID_MISMATCH);
            return deleteResponse;
        }
    }
}
