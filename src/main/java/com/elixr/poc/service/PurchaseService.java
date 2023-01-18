package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdFormatException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.repository.PurchaseRepository;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.rest.response.PurchaseGetResponse;
import com.elixr.poc.rest.response.PurchaseResponse;
import org.springframework.stereotype.Service;
import java.util.Optional;
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

    public PurchaseResponse createPurchase(Purchase purchase) {
        savePurchase(purchase);
        return PurchaseResponse.purchaseBuilder().success(true).id(purchase.getId()).userName(purchase.getUserName())
                .product(purchase.getProduct()).amount(purchase.getAmount()).date(purchase.getDate()).build();
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

    /**
     * Retrieving all the purchase
     *
     * @return
     */
    public PurchaseGetResponse getAllPurchases() {
        return PurchaseGetResponse.builder().success(true).purchases(purchaseRepository.findAll()).build();
    }

    /**
     * If the PurchaseId exists then delete the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws NotFoundException
     */
    public boolean deletePurchaseDetails(String purchaseId) throws com.elixr.poc.common.exception.NotFoundException {
        boolean success = false;
        UUID purchaseuuid = uuidValidation(purchaseId);
        boolean purchaseRecordExists = purchaseRepository.existsById(purchaseuuid);
        if (purchaseRecordExists) {
            purchaseRepository.deleteById(purchaseuuid);
            success = true;
        } else {
            throw new com.elixr.poc.common.exception.NotFoundException(ApplicationConstants.ID_MISMATCH);
        }
        return success;
    }

    private UUID uuidValidation(String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            return uuid;
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IdFormatException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_INVALID_ID_FORMAT.getKey()));
        }
    }

    /**
     * If the PurchaseId exists then update the PurchaseId or else send Id is mismatched. * * @param purchaseId * @return * @throws NotFoundException
     */
    public Purchase purchaseUpdate(String purchaseId, PurchaseRequest purchaseDetails) {
        UUID uuid = uuidValidation(purchaseId);
        Purchase purchase = purchaseRepository.findById(uuid).orElseThrow(() -> new NotFoundException(MessagesUtil
                .getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXISTS.getKey(),
                MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_PURCHASE_ID.getKey()))));
        purchase.setUserName(purchaseDetails.getUserName());
        purchase.setProduct(purchaseDetails.getProduct());
        purchase.setAmount(purchaseDetails.getAmount());
        purchase.setDate(purchaseDetails.getDate());
        final Purchase updatedPurchase = purchaseRepository.save(purchase);
        return updatedPurchase;
    }
}
