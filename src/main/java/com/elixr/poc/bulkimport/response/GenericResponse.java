package com.elixr.poc.bulkimport.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class GenericResponse {
    private String status;
    @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
    private List<RowResponse> data;
    @JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
    private String errorMessage;
}
