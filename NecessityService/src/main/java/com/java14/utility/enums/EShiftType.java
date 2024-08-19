package com.java14.utility.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EShiftType {
    SABAH_ALTI_ONIKI("Sabah Vardiyası: 06:00 - 12:00\nKahvaltı Molası: 08:00 - 08:15\nÇay Molası: 10:00 - 10:15"),

    OGLEDENSONRA_ONIKI_ALTI("Öğleden Sonra Vardiyası: 12:00 - 18:00\nÖğle Yemeği Molası: 14:00 - 14:30\nÇay Molası: 16:00 - 16:15"),

    AKSAM_ALTI_ONIKI("Akşam Vardiyası: 18:00 - 00:00\nAkşam Yemeği Molası: 20:00 - 20:30\nÇay Molası: 22:00 - 22:15"),

    GECE_ONIKI_ALTI("Gece Vardiyası: 00:00 - 06:00\nGece Kahve Molası: 02:00 - 02:15\nÇay Molası: 04:00 - 04:15"),

    TAM_GUN_DOKUZ_ALTI("Tam Gün Vardiyası: 09:00 - 18:00\nÖğle Yemeği Molası: 12:00 - 12:30\nÇay Molası: 15:00 - 15:15"),

    TAM_GUN_SEKIZ_BES("Tam Gün Vardiyası: 08:00 - 17:00\nÖğle Yemeği Molası: 12:00 - 12:30\nÇay Molası: 14:30 - 14:45"),

    HAFTASONU_DOKUZ_BIR("Hafta Sonu Vardiyası: 09:00 - 13:00\nÇay Molası: 11:00 - 11:15"),

    HAFTASONU_SEKIZ_ONIKI("Hafta Sonu Sabah Vardiyası: 08:00 - 12:00\nÇay Molası: 10:00 - 10:15"),

    HAFTASONU_OGLEDENSONRA("Hafta Sonu Öğleden Sonra Vardiyası: 13:00 - 18:00\nÇay Molası: 15:00 - 15:15\nKısa Molası: 17:00 - 17:05");






    private final String value;

    EShiftType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EShiftType fromValue(String value) {
        for (EShiftType type : EShiftType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }
}
