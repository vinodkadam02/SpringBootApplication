package com.elixr.poc.service;

import com.elixr.poc.common.MessageKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.common.exception.GlobalException;
import com.elixr.poc.repository.PurchaseRepository;
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
     * If the PurchaseId exists then delete the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws GlobalException
     */
    public boolean deletePurchaseDetails(UUID purchaseId) throws GlobalException {
        boolean success = false;
        boolean purchaseRecordExists = purchaseRepository.existsById(purchaseId);
        if (purchaseRecordExists) {
            purchaseRepository.deleteById(purchaseId);
            success = true;
        } else {
            throw new GlobalException(MessagesUtil.getMessage(MessageKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey()));
        }
        return success;
    }

    /**
     * Finding Purchase by purchaseId and returning the purchase.
     */
    public Purchase getPurchaseByPurchaseId(UUID purchaseId) throws GlobalException {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);
        return purchase.orElseThrow(() -> new GlobalException(MessagesUtil.getMessage(MessageKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey())));
    }
}
