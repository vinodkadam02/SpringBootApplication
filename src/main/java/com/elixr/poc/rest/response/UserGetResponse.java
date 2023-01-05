package com.elixr.poc.rest.response;

import com.elixr.poc.data.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserGetResponse extends UserResponse {

    private List<User> users;

    @Builder(builderMethodName = "newGetBuilder")
    public UserGetResponse(boolean success, List<User> users) {
        super(success);
        this.users = users;
    }
}
