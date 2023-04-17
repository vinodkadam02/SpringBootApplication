package com.elixr.poc.common.enums;

import lombok.Getter;

@Getter
public enum FileMessageEnum {
    ASSIGNED_NEW_DOCTOR("Assigned New doctor successfully"),
    ASSIGN_NEW_DOCTOR_FAILED("Assigning New Doctor failed Due to Patient does not exist in DB"),
    DOCTOR_ALREADY_EXIST("Doctor Already Exist to this Patient"),
    DOCTOR_NOT_EXIST("Doctor does not exist in db"),
    FAILURE("Fail"),
    NO_MATCHING_ACTION_FOUND("No matching Action Found"),
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

    FileMessageEnum(String fileKey) {
        this.fileKey = fileKey;
    }
}
