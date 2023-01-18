package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdFormatException;
import com.elixr.poc.common.exception.IdNotFoundException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.repository.PurchaseRepository;
import com.elixr.poc.rest.request.PurchaseRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * Validating UUID format.
     *
     * @param purchaseId
     * @return
     */
    private UUID uuidValidation(String purchaseId) {
        try {
            UUID uuid = UUID.fromString(purchaseId);
            return uuid;
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IdFormatException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_INVALID_ID_FORMAT.getKey()));
        }
    }

    /**
     * If the PurchaseId exists then delete the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws IdNotFoundException
     */
    public boolean deletePurchaseDetails(String purchaseId) {
        UUID uuid = uuidValidation(purchaseId);
        boolean purchaseRecordExists = purchaseRepository.existsById(uuid);
        if (!purchaseRecordExists) {
            throw new IdNotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey(), "Purchase"));
        }
        purchaseRepository.deleteById(uuid);
        return true;
    }

    /**
     * If the PurchaseId exists then update the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws IdNotFoundException
     */
    public Purchase purchaseUpdate(String purchaseId, PurchaseRequest purchaseDetails) {
        UUID uuid = uuidValidation(purchaseId);
        Purchase purchase = purchaseRepository.findById(uuid).orElseThrow(() -> new IdNotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey(), MessagesKeyEnum.ENTITY_PURCHASE_ID.getKey())));
        purchase.setUserName(purchaseDetails.getUserName());
        purchase.setProduct(purchaseDetails.getProduct());
        purchase.setAmount(purchaseDetails.getAmount());
        purchase.setDate(purchaseDetails.getDate());
        final Purchase updatedPurchase = purchaseRepository.save(purchase);
        return updatedPurchase;
    }
    /**
     * Finding Purchase by purchaseId and returning the purchase.
     */
    public Purchase getPurchaseByPurchaseId(String purchaseId) {
        UUID uuid = uuidValidation(purchaseId);
        Optional<Purchase> purchase = purchaseRepository.findById(uuid);
        return purchase.orElseThrow(() -> new IdNotFoundException(MessagesUtil
                .getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey(), "Purchase")));
    }
}
