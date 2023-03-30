package com.elixr.poc.bulkimport.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "patients")
public class Patient {
    @Id
    private String patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientAge;
    private String patientAddress;
    private List<String> doctorList;
}
