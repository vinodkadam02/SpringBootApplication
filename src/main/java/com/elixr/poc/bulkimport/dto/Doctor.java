package com.elixr.poc.bulkimport.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "doctor")
public class Doctor {
    @Id
    private String id;
    private String doctorName;
}
