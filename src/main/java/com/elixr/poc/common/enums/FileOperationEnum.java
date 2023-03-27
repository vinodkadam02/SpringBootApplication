package com.elixr.poc.common.enums;

import lombok.Getter;

@Getter
public enum FileOperationEnum {
    ACTION("Action"),
    ASSIGNED_NEW_DOCTOR("Assigned New doctor successfully"),
    ASSIGN_NEW_DOCTOR_FAILED("Assigning New Doctor failed Due to Patient does not exist in DB"),
    DOCTOR_ID("DoctorId"),
    DOCTOR_ALREADY_EXIST("Doctor Already Exist to this Patient"),
    FAILURE("Fail"),
    PATIENT_ADDRESS("PatientAddress"),
    PATIENT_AGE("PatientAge"),
    PATIENT_ID("PatientId"),
    PATIENT_FIRSTNAME("PatientFirstName"),
    PATIENT_LASTNAME("PatientLastName"),
    RECORD_CREATED("Record created successfully"),
    RECORD_CREATION_FAILED("Failed to create Record Due to Doctor does not exist in DB"),
    RECORD_DELETED("Record Deleted successfully"),
    RECORD_DELETE_FAILED("Deletion failed Due to Patient does not exist in DB"),
    RECORD_UPDATED("Record Update Successfully"),
    RECORD_UPDATE_FAILED("Failed to Update Record Due to Patient does not exist in DB"),
    REMOVE_DOCTOR("Removed Doctor Successfully"),
    REMOVE_DOCTOR_FAILED("Remove Doctor failed"),
    SUCCESS("Success");
    private final String fileKey;

    FileOperationEnum(String fileKey) {
        this.fileKey = fileKey;
    }
}
