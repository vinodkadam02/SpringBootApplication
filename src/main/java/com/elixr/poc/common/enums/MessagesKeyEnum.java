package com.elixr.poc.common.enums;

import lombok.Getter;

/**
 * Enum to use all message properties
 */
@Getter
public enum MessagesKeyEnum {
    ENTITY_DOES_NOT_EXIST("entity.does.not.exist"),
    ENTITY_DOCTOR_ID("entity.doctor.id"),
    ENTITY_DELETED_SUCCESSFULLY("entity.deleted.successfully"),
    ENTITY_FILE_ADD_OPERATION("entity.file.add.operation"),
    ENTITY_FILE_ASSIGN_NEW_DOCTOR("entity.file.assign.new.doctor"),
    ENTITY_FILE_DOES_NOT_EXIST("entity.file.does.not.exist"),
    ENTITY_FILE_DELETE_OPERATION("entity.file.delete.operation"),
    ENTITY_FILE_EXTENSION("entity.file.extension"),
    ENTITY_FILE_EXTENSION_ERROR_MESSAGE("entity.file.extension.error.message"),
    ENTITY_FILE_REMOVE_DOCTOR("entity.file.remove.doctor"),
    ENTITY_FILE_UPDATE_OPERATION("entity.file.update.operation"),
    ENTITY_FILE_UPLOADED_SUCCESSFULLY("entity.file.uploaded.successfully"),
    ENTITY_INVALID_ID_FORMAT("entity.invalid.id.format"),
    ENTITY_MANDATORY_FIELD_MISSING("entity.mandatory.field.missing"),
    ENTITY_PATIENT_ID("entity.patient.id"),
    ENTITY_PURCHASE_ID("entity.purchase.Id"),
    ENTITY_PATIENT_DOES_NOT_EXIST("entity.patient.does.not.exist"),
    ENTITY_USER_DOES_NOT_EXISTS("entity.user.does.not.exists"),
    ENTITY_USER_EXISTS("entity.user.exists"),
    ENTITY_USER_ID("entity.user.id");

    private final String key;

    MessagesKeyEnum(String key) {
        this.key = key;
    }
}
