package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.ErrorResponse;

import java.util.List;

@Builder
@Data
public class PostErrorResponse extends AppResponse {
   private List<String> errorMessage;
}
