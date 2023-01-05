package com.elixr.poc.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {
        public static final String USER_NAME_MISSING_IN_PURCHASE = "User name is mandatory";
        public static final String AMOUNT_MISSING_IN_PURCHASE = "Amount is mandatory";
        public static final String PRODUCT_NAME_IS_MISSING_IN_PURCHASE = "Product name are mandatory";
        public static final String DATE_MISSING_IN_PURCHASE = "Date are mandatory";
}
