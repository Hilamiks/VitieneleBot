package com.hilamiks.vitienelebot.logic;

import java.util.Arrays;

public enum CallbackConstant {
    HOME,

    NEW_CHAR,
    LIST_CHARS,
    CHANGE_NAME,
    CHANGE_CLASS,
    CHANGE_RACE,
    SAVE_CHARACTER,
    DISCARD_CHARACTER,
    ADD_IMAGE,
    SET_RACE,
    SET_CLASS,

    SET_SELECTED_CHARACTER,
    DELETE_CHARACTER,
    VIEW_CHARACTER,

    CHANGE_PARAMETER
    ;


    public static CallbackConstant of(String constantName) {
        return Arrays.stream(values())
            .filter(constant -> constant.name().equals(constantName))
            .findFirst().orElse(LIST_CHARS);
    }
}
