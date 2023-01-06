package com.elixr.poc.service;

import com.elixr.poc.data.Purchase;
import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.exception.NoRecordFoundException;
import com.elixr.poc.repository.PurchaseRepository;
import com.elixr.poc.rest.response.PurchaseGetResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * service class to interact with controllers for all purchase related operations.
 */

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {

        this.purchaseRepository = purchaseRepository;
    }

    public void createPurchase(Purchase purchase)
    {
        savePurchase(purchase);
    }

    /**
     * Calling purchase repository to store the valid user information to the database
     *
     * @param purchase
     * @return
     */
    private void savePurchase(Purchase purchase) {
        if (purchase.getId() == null || purchase.getId().toString().isEmpty()) {
            purchase.setId(UUID.randomUUID());
        }
         this.purchaseRepository.save(purchase);
    }
    public PurchaseGetResponse getAllPurchases()
    {
        return PurchaseGetResponse.builder().success(true).purchase(purchaseRepository.findAll()).build();
    }

    /**
     * If the PurchaseId exists then delete the PurchaseId or else send Id is mismatched.
     *
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
