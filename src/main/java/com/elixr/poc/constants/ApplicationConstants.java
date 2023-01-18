package com.elixr.poc.constants;

/**
 * This constant file holds all the error messages for all the end point
 */
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstants {

    public static final String FIRST_NAME_MISSING_IN_USER = "First name is mandatory";
    public static final String USER_NAME_MISSING = "Username is mandatory";
    public static final String LAST_NAME_MISSING_IN_USER = "Last name is mandatory";
    public static final String SUCCESSFULLY_DELETED = "Successfully Deleted";
    public static final String ID_MISMATCH = "The ID is Not Matching";
    public static final String AMOUNT_MISSING_IN_PURCHASE = "Amount is mandatory";
    public static final String PRODUCT_NAME_IS_MISSING_IN_PURCHASE = "Product name are mandatory";
    public static final String DATE_MISSING_IN_PURCHASE = "Date are mandatory";
     public static final String DELETED = "Deleted";
}
