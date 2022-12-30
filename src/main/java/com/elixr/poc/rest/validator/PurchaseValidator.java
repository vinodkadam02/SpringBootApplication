package com.elixr.poc.rest.validator;
import com.elixr.poc.Constant.ErrorConstants;
import com.elixr.poc.rest.request.PurchaseRequest;
import java.util.ArrayList;
import java.util.List;

public class PurchaseValidator {
    public List<String> validate(PurchaseRequest newPurchaseRequest) {

        List<String> errorList = new ArrayList<>();
        if (newPurchaseRequest.getUserName() == null || newPurchaseRequest.getUserName().isEmpty()) {
            errorList.add(ErrorConstants.USER_NAME_MISSING_IN_PURCHASE);
        }
        if (newPurchaseRequest.getProduct() == null || newPurchaseRequest.getProduct().isEmpty()) {
            errorList.add(ErrorConstants.PRODUCT_NAME_IS_MISSING_IN_PURCHASE);
        }
        if (newPurchaseRequest.getAmount() == null || newPurchaseRequest.getAmount().isEmpty()) {
            errorList.add(ErrorConstants.AMOUNT_MISSING_IN_PURCHASE);
        }
        if (newPurchaseRequest.getDate() == null || newPurchaseRequest.getDate().isEmpty()) {
            errorList.add(ErrorConstants.DATE_MISSING_IN_PURCHASE);
        }
        return errorList;
    }
}
