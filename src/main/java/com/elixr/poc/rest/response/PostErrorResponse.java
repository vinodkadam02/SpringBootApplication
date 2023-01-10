package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PostErrorResponse extends ErrorResponse{
   private List<String> errorMessage;
}
