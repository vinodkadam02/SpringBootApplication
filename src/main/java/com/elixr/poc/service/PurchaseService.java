package com.elixr.poc.service;

import com.elixr.poc.Constant.ApplicationConstants;
import com.elixr.poc.Repository.PurchaseRepository;
import com.elixr.poc.exception.NoRecordFoundException;
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
     * @param purchaseId
     * @return
     * @throws NoRecordFoundException
     */
    public boolean deletePurchaseDetails(UUID purchaseId) throws NoRecordFoundException {
        boolean success = false;
        boolean purchaseRecordExists = purchaseRepository.existsById(purchaseId);
        if (purchaseRecordExists) {
            purchaseRepository.deleteById(purchaseId);
            success = true;
        } else {
            throw new NoRecordFoundException(ApplicationConstants.ID_MISMATCH);
        }
        return success;
    }
}
