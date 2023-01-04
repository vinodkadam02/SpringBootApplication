package com.elixr.poc.constants;

/* this constant file holds all the error messages for all the end points */

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstants {

    public static final String FIRST_NAME_MISSING_IN_USER = "First name is mandatory";
    public static final String USER_NAME_MISSING_IN_USER = "User name is mandatory";
    public static final String LAST_NAME_MISSING_IN_USER = "Last name is mandatory";
    public static final String SUCCESSFULLY_DELETED = "Successfully Deleted";
    public static final String ID_MISMATCH = "The ID is Not Matching";
}
