package com.java14.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ELeaveType {
    VEFAT_IZNI("Vefat İzni"),
    YILLIK_IZIN("Yıllık İzin"),
    HASTALIK_IZNI("Hastalık İzni"),
    DOGUM_IZNI("Doğum İzni"),
    SUT_IZNI("Süt İzni"),
    EGITIM_IZNI("Eğitim İzni"),
    EVLILIK_IZNI("Evlilik İzni"),
    ASKERLIK_IZNI("Askerlik İzni"),
    UCRETSIZ_IZIN("Ücretsiz İzin"),
    MAZERET_IZNI("Mazeret İzni");

    private final String value;

    ELeaveType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ELeaveType fromValue(String value) {
        for (ELeaveType type : ELeaveType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }
}
