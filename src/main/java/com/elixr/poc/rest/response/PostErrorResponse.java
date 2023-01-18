package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostErrorResponse extends AppResponse {
    List<String> errorMessage;
}
