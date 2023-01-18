package com.elixr.poc.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Sending list of error message as per invalid field
 */

@Data
public class PostErrorResponse extends AppResponse {
   private List<String> errorMessage;
   @Builder
   public PostErrorResponse(boolean success,List<String> errorMessage){
      setSuccess(success);
      this.errorMessage = errorMessage;
   }

}
