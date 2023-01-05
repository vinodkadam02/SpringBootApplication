package com.elixr.poc.rest.request;

import com.elixr.poc.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 *  This Class holds the request attributes for user related end points
 */

@Data
public class UserRequest {

    @NotBlank(message = ApplicationConstants.USER_NAME_MISSING_IN_USER)
    private String userName;
    @NotBlank(message = ApplicationConstants.FIRST_NAME_MISSING_IN_USER)
    private String firstName;
   @NotBlank(message = ApplicationConstants.LAST_NAME_MISSING_IN_USER)
    private String lastName;
}
