package com.elixr.poc.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.IdNotFoundException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.data.Purchase;
import com.elixr.poc.repository.PurchaseRepository;
import com.elixr.poc.rest.request.PurchaseRequest;
import com.elixr.poc.rest.response.PurchaseGetResponse;
import com.elixr.poc.rest.response.PurchaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {

        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseResponse createPurchase(Purchase purchase) {
        savePurchase(purchase);
        return PurchaseResponse.purchaseBuilder().success(true).id(purchase.getId()).userName(purchase.getUserName()).product(purchase.getProduct()).amount(purchase.getAmount()).date(purchase.getDate()).build();
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
        }
        purchase = purchaseRepository.save(purchase);
        return purchase;
    }

    /**
     * If the PurchaseId exists then delete the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws IdNotFoundException
     */
    public boolean deletePurchaseDetails(UUID purchaseId) throws IdNotFoundException {
        boolean success;
        boolean purchaseRecordExists = purchaseRepository.existsById(purchaseId);
        if (purchaseRecordExists) {
            purchaseRepository.deleteById(purchaseId);
            success = true;
        } else {
            throw new IdNotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS.getKey()));
        }
        return success;
    }

    /**
     * If the PurchaseId exists then update the PurchaseId or else send Id is mismatched.
     *
     * @param purchaseId
     * @return
     * @throws IdNotFoundException
     */
    public Purchase purchaseUpdate(UUID purchaseId, PurchaseRequest purchaseDetails) throws IdNotFoundException {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() ->
                new IdNotFoundException("Purchase not found on :: " + purchaseId));
        purchase.setUserName(purchaseDetails.getUserName());
        purchase.setProduct(purchaseDetails.getProduct());
        purchase.setAmount(purchaseDetails.getAmount());
        purchase.setDate(purchaseDetails.getDate());
        final Purchase updatedPurchase = purchaseRepository.save(purchase);
        return updatedPurchase;
    }

    /**
     * It checks if the user is preset in the userId
     * or else it will throw an error
     * @param userName
     * @return
     */
    public PurchaseGetResponse getPurchaseByUserName(String userName) {
        List<Purchase> existingUser = purchaseRepository.findPurchasesByUserName(userName);
        if (existingUser.isEmpty()) {
            throw new IdNotFoundException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_ID_DOES_NOT_EXISTS
                    .getKey(), "Purchases"));
        }

        return PurchaseGetResponse.builder().success(true).purchases(existingUser).build();
    }
}
