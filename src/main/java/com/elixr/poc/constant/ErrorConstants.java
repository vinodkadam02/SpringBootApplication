package com.elixr.poc.constant;

/* this constant file holds all the error messages for all the end points */

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorConstants {

    public static final String FIRST_NAME_MISSING_IN_USER = "First name is mandatory";
    public static final String USER_NAME_MISSING_IN_USER = "User name is mandatory";
    public static final String LAST_NAME_MISSING_IN_USER = "Last name is mandatory";


}
