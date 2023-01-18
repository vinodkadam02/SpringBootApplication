package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdFormatException;
import com.elixr.poc.common.exception.NotFoundException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.repository.PurchaseRepository;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.rest.response.PurchaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;
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
     * @throws NotFoundException
     */
    public boolean deletePurchaseDetails(String purchaseId) {
        UUID uuid = uuidValidation(purchaseId);
        boolean purchaseRecordExists = purchaseRepository.existsById(uuid);
        if (!purchaseRecordExists) {
            throw new NotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXISTS.getKey(), "Purchase"));
        }
        purchaseRepository.deleteById(uuid);
        return true;
    }

    /**
     * If the PurchaseId exists then update the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws NotFoundException
     */
    public Purchase purchaseUpdate(String purchaseId, PurchaseRequest purchaseDetails) {
        UUID uuid = uuidValidation(purchaseId);
        Purchase purchase = purchaseRepository.findById(uuid).orElseThrow(() -> new NotFoundException
                (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXISTS.getKey(),
                        MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_PURCHASE_ID.getKey()))));
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
        UUID uuid = uuidValidation(purchaseId.toString());
        Optional<Purchase> purchase = purchaseRepository.findById(uuid);
        return purchase.orElseThrow(() -> new NotFoundException(MessagesUtil
                .getMessage(MessagesKeyEnum.ENTITY_DOES_NOT_EXISTS.getKey(), "Purchase")));

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

    private Purchase savePurchase(Purchase purchase) {
        if (purchase.getId() == null || purchase.getId().toString().isEmpty()) {
            purchase.setId(UUID.randomUUID());
            purchase = purchaseRepository.save(purchase);
        }
        return purchase;
    }
    /**
     * Using UserName finding the purchaseDetails or else send UserName does not exist
     *
     * @param userName
     * @return
     */
    public List<Purchase> getPurchaseByUserName(String userName) {
        List<Purchase> existingUser = purchaseRepository.findPurchasesByUserName(userName);
        if (existingUser.isEmpty()) {
            throw new NotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_USER_DOES_NOT_EXISTS.getKey()));
        }
        return existingUser;
    }
}
