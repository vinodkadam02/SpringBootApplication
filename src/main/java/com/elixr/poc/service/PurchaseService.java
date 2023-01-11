package com.elixr.poc.service;

import com.elixr.poc.common.exception.IdNotFoundException;
import com.elixr.poc.constants.ApplicationConstants;
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
     * @throws IdNotFoundException
     */
    public boolean deletePurchaseDetails(UUID purchaseId) throws IdNotFoundException {
        boolean success = false;
        boolean purchaseRecordExists = purchaseRepository.existsById(purchaseId);
        if (purchaseRecordExists) {
            purchaseRepository.deleteById(purchaseId);
            success = true;
        } else {
            throw new IdNotFoundException(ApplicationConstants.ID_MISMATCH);
        }
        return success;
    }

}
