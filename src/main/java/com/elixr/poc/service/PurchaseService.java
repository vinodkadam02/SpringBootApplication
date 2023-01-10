package com.elixr.poc.service;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.exception.GlobalException;
import com.elixr.poc.repository.PurchaseRepository;
import com.elixr.poc.rest.request.PurchaseRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * If the PurchaseId exists then delete the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws GlobalException
     */
    public boolean deletePurchaseDetails(UUID purchaseId) throws GlobalException {
        boolean success;
        boolean purchaseRecordExists = purchaseRepository.existsById(purchaseId);
        if (purchaseRecordExists) {
            purchaseRepository.deleteById(purchaseId);
            success = true;
        } else {
            throw new GlobalException(ApplicationConstants.ID_MISMATCH);
        }
        return success;
    }

    /**
     * If the PurchaseId exists then update the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws GlobalException
     */
    public Purchase updateUserPartially(UUID purchaseId, PurchaseRequest purchaseDetails) throws GlobalException {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new GlobalException("Purchase not found on :: " + purchaseId));
        purchase.setUserName(purchaseDetails.getUserName());
        purchase.setProduct(purchaseDetails.getProduct());
        purchase.setAmount(purchaseDetails.getAmount());
        purchase.setDate(purchaseDetails.getDate());
        final Purchase updatedPurchase = purchaseRepository.save(purchase);
        return updatedPurchase;
    }
}
