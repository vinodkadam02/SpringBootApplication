package com.elixr.poc.service;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.exception.NoRecordFoundException;
import com.elixr.poc.repository.PurchaseRepository;
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
     * @throws NoRecordFoundException
     */
    public boolean deletePurchaseDetails(UUID purchaseId) throws NoRecordFoundException {
        boolean success;
        boolean purchaseRecordExists = purchaseRepository.existsById(purchaseId);
        if (purchaseRecordExists) {
            purchaseRepository.deleteById(purchaseId);
            success = true;
        } else {
            throw new NoRecordFoundException(ApplicationConstants.ID_MISMATCH);
        }
        return success;
    }

    /**
     * If the PurchaseId exists then update the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws NoRecordFoundException
     */
    public Purchase updateUserPartially(UUID purchaseId, Purchase purchaseDetails) throws NoRecordFoundException {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new NoRecordFoundException("User not found on :: " + purchaseId));
        purchase.setUserName(purchaseDetails.getUserName());
        purchase.setProduct(purchaseDetails.getProduct());
        purchase.setAmount(purchaseDetails.getAmount());
        purchase.setDate(purchaseDetails.getDate());
        final Purchase updatedPurchase = purchaseRepository.save(purchase);
        return updatedPurchase;
    }
}
