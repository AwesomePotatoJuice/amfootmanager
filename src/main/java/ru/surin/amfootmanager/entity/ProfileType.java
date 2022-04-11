package ru.surin.amfootmanager.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum ProfileType implements EnumClass<String> {

    COACH("Coach"),
    PLAYER("Player"),
    FAN("Fan"),
    ANONYMOUS("Anonymous");

    private String id;

    ProfileType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ProfileType fromId(String id) {
        for (ProfileType at : ProfileType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}