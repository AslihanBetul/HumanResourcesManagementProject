package com.java14.utility.enums;

public enum EShiftType {
    MORNING_SIX_TO_TWELVE("Morning Shift: 06:00 - 12:00"),
    AFTERNOON_TWELVE_TO_SIX("Afternoon Shift: 12:00 - 18:00"),
    EVENING_SIX_TO_TWELVE("Evening Shift : 18:00 - 00:00"),
    NIGHT_TWELVE_TO_SIX("Night Shift: 00:00 - 06:00"),
    FULL_DAY_NINE_TO_SIX("Full Day Shift: 09:00 - 18:00"),
    FULL_DAY_EIGHT_TO_FIVE("Full Day Shift: 08:00 - 17:00"),
    WEEKEND_NINE_TO_ONE("Weekend Shift: 09:00 - 13:00"),
    WEEKEND_EIGHT_TO_TWELVE("Weekend Morning Shift: 08:00- 12:00"),
    WEEKEND_AFTERNOON("Weekend Afternoon Shift: 13:00 - 18:00"),

    ;



    private final String description;

    EShiftType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
