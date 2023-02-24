package com.elixr.poc.rest.response;

import com.elixr.poc.data.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Sends successful response to the user while retrieval operation.
 */
@Data
public class GetAllResponse extends AppResponse {
    private List<User> users;

    @Builder
    public GetAllResponse(boolean success,List<User> users) {
       setSuccess(success);
        this.users = users;
    }
}
