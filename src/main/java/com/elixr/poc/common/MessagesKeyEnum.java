package com.elixr.poc.common;

import lombok.Getter;

/**
 * Enum to use all message properties
 */
@Getter
public enum MessagesKeyEnum {

    ENTITY_DELETED_SUCCESSFULLY("entity.deleted.successfully"),
    ENTITY_ID_NOT_EXISTS("entity.id.dose.not.exists"),
    ENTITY_MANDATORY_FIELD_MISSING("entity.mandatory.field.missing"),
    ENTITY_USER_EXISTS("entity.user.exists");

    MessagesKeyEnum(String key) {
        this.key=key;
    }
    public String key;
}
