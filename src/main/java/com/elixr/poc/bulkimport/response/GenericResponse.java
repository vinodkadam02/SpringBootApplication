package com.elixr.poc.bulkimport.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class GenericResponse {
    private String status;
    private List<RowResponse> data;
}
