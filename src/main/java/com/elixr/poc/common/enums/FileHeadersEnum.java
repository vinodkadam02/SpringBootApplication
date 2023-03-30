package com.elixr.poc.common.enums;

import lombok.Getter;

/**
 * These Enums are Headers of Excel File, this enum is used to iterate in hashmap as a key in same order Excel headers are.
 */
@Getter
public enum FileHeadersEnum {
    ACTION("Action"),
    PATIENT_ID("PatientId"),
    PATIENT_FIRSTNAME("PatientFirstName"),
    PATIENT_LASTNAME("PatientLastName"),
    PATIENT_AGE("PatientAge"),
    PATIENT_ADDRESS("PatientAddress"),
    DOCTOR_ID("DoctorId");

    private final String Key;

    FileHeadersEnum(String key) {
        this.Key = key;
    }
}
