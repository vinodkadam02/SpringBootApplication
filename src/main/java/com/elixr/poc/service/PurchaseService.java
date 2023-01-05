package com.elixr.poc.service;

import com.elixr.poc.constants.ApplicationConstants;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.exception.NoRecordFoundException;
import com.elixr.poc.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
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

    /**
     * If the PurchaseId exists then update the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @param fields
     * @return
     * @throws NoRecordFoundException
     */
    public Purchase updatePurchase(UUID purchaseId, Map<String, String> fields) throws NoRecordFoundException {
        Optional<Purchase> purchaseRecordExists = purchaseRepository.findById(purchaseId);
        if (purchaseRecordExists.isPresent()) {
            for (Map.Entry<String, String> entry : fields.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Field field = ReflectionUtils.findField(Purchase.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, purchaseRecordExists.get(), value);
            }
            return purchaseRepository.save(purchaseRecordExists.get());
        } else {
            throw new NoRecordFoundException(ApplicationConstants.ID_MISMATCH);
        }
    }
}
