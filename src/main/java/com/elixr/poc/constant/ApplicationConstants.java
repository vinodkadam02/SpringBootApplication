package com.elixr.poc.constant;

/**
 * This constant file holds all the error messages for all the end point
 */
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationConstants {

    public static final String FIRST_NAME_MISSING_IN_USER = "First name is mandatory";
    public static final String USER_NAME_MISSING_IN_USER = "User name is mandatory";
    public static final String LAST_NAME_MISSING_IN_USER = "Last name is mandatory";
    public static final String DELETED = "Deleted";
    public static final String ID_MISMATCH = "The ID is not Matching";
}
