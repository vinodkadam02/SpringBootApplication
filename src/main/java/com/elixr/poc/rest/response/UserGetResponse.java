package com.elixr.poc.rest.response;

import com.elixr.poc.data.User;
import jakarta.validation.constraints.Negative;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserGetResponse {

    private boolean success;
    private List<User> users;
}
