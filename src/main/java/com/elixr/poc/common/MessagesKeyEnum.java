package com.elixr.poc.common;

import lombok.Getter;

/**
 * Enum to use all message properties
 */
@Getter
public enum MessagesKeyEnum {
    ENTITY_DELETED_SUCCESSFULLY("entity.deleted.successfully"),
    ENTITY_ID_DOES_NOT_EXISTS("entity.id.does.not.exists"),
    ENTITY_MANDATORY_FIELD_MISSING("entity.mandatory.field.missing"),
    ENTITY_USER_EXISTS("entity.user.exists");

    MessagesKeyEnum(String key) {
        this.key=key;
    }
    public String key;
}
