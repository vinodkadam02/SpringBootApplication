package com.elixr.poc.rest.validator;

import com.elixr.poc.constant.ErrorConstants;
import com.elixr.poc.rest.request.UserRequest;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class UserValidator {
    public List<String> validate(UserRequest newUserRequest) {
        List<String> errorList = new ArrayList<>();
        if (newUserRequest.getFirstName() == null || newUserRequest.getFirstName().isEmpty()) {
            errorList.add(ErrorConstants.FIRST_NAME_MISSING_IN_USER);
        }
        if (newUserRequest.getUserName() == null || newUserRequest.getUserName().isEmpty()) {
            errorList.add(ErrorConstants.USER_NAME_MISSING_IN_USER);
        }
        if (newUserRequest.getLastName() == null || newUserRequest.getLastName().isEmpty()) {
            errorList.add(ErrorConstants.LAST_NAME_MISSING_IN_USER);
        }

        return errorList;
    }

}
