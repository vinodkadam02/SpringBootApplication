package com.elixr.poc.common;

import lombok.Getter;

/**
 * Enum to use all message properties
 */
@Getter
public enum MessagesKeyEnum {
    ENTITY_DELETED_SUCCESSFULLY("entity.deleted.successfully"),
    ENTITY_DOES_NOT_EXIST("entity.does.not.exist"),
    ENTITY_INVALID_ID_FORMAT("entity.invalid.id.format"),
    ENTITY_MANDATORY_FIELD_MISSING("entity.mandatory.field.missing"),
    ENTITY_USER_EXISTS("entity.user.exists"),
    ENTITY_USER_ID("entity.user.id");

    MessagesKeyEnum(String key) {
        this.key=key;
    }
    private String key;
}
