package com.elixr.poc.common;

import lombok.Getter;

/**
 * Enum to use all message properties.
 */
@Getter
public enum MessageKeyEnum {
    ENTITY_DELETED_SUCCESSFULLY("entity.deleted.successfully"),
    ENTITY_ID_DOES_NOT_EXISTS("entity.id.does.not.exists"),
    ENTITY_MANDATORY_FIELD_MISSING("entity.mandatory.field.missing"),
    ENTITY_USER_EXISTS("entity.user.exists");

    public String key;

    MessageKeyEnum(String key) {
        this.key = key;
    }
}
