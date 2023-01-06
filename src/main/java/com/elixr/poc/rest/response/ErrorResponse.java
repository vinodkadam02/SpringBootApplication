package com.elixr.poc.rest.response;
import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * This class object sends the proper error message if there is any invalid user  request comes
 */
@Data
@Builder
public class ErrorResponse extends UserResponse {

    private List<String> errorMessage;
}
