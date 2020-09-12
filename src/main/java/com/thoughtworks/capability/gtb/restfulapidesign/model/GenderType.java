package com.thoughtworks.capability.gtb.restfulapidesign.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderType {
    MALE("男"),
    FEMALE("女");

    private final String gender;

    GenderType(String gender) {
        this.gender = gender;
    }

    @JsonValue
    public String getGender() {
        return gender;
    }
}
