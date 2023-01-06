package com.elixr.poc.rest.request;

import com.elixr.poc.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;
/**
 *  This Class holds the request attributes for user related end points
 */

@Data
public class UserRequest {
    @Id
    private UUID id;
    @NotBlank(message = ApplicationConstants.USER_NAME_MISSING)
    private String userName;
    @NotBlank(message = ApplicationConstants.FIRST_NAME_MISSING_IN_USER)
    private String firstName;
   @NotBlank(message = ApplicationConstants.LAST_NAME_MISSING_IN_USER)
    private String lastName;
}
