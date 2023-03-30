package com.elixr.poc.bulkimport.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RowResponse {
    private String status;
    @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
    private int row;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String successMessage;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errorMessage;
}
