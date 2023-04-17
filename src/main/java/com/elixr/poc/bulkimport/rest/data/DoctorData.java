package com.elixr.poc.bulkimport.rest.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DoctorData {
    private String id;
    private String doctorName;
    private List<String> patients;
}
