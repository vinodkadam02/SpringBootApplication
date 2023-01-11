package com.elixr.poc.rest.response;

import com.elixr.poc.data.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Sends successful response to the user while retrieval operation
 */
@Data
public class GetAllResponse extends UserResponse {
    private List<User> users;

    @Builder(builderMethodName = "newGetBuilder")
    public GetAllResponse(List<User> users) {
       setSuccess(true);
        this.users = users;
    }
}
