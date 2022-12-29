package com.elixr.poc.rest.validator;

import com.elixr.poc.constant.ErrorConstants;
import com.elixr.poc.rest.request.UserRequest;
import lombok.Builder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Builder
public class UserValidator {
    public List<String> validate(UserRequest newUserRequest) {
        List<String> errorList = new ArrayList<>();
        if (StringUtils.isEmpty(newUserRequest.getFirstName())) {
            errorList.add(ErrorConstants.FIRST_NAME_MISSING_IN_USER);
        }
        if (StringUtils.isEmpty(newUserRequest.getUserName())) {
            errorList.add(ErrorConstants.USER_NAME_MISSING_IN_USER);
        }
        if (StringUtils.isEmpty(newUserRequest.getLastName())) {
            errorList.add(ErrorConstants.LAST_NAME_MISSING_IN_USER);
        }

        return errorList;
    }

}
