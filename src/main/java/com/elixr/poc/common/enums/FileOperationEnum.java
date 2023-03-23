package com.elixr.poc.common.enums;

import lombok.Getter;

@Getter
public enum FileOperationEnum {
    ACTION("Action"),
    DOCTOR_ID("DoctorId"),
    PATIENT_ADDRESS("PatientAddress"),
    PATIENT_AGE("PatientAge"),
    PATIENT_ID("PatientId"),
    PATIENT_FIRSTNAME("PatientFirstName"),
    PATIENT_LASTNAME("PatientLastName");

    private final String fileKey;

    FileOperationEnum(String fileKey) {
        this.fileKey = fileKey;
    }
}
