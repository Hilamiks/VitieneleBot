package com.hilamiks.vitienelebot.telegram.user;

import java.util.Arrays;

public enum UserState {
    BASE,
    AWAITING_NAME,

    AWAITING_PARAMETER_VALUE_OF_ATTACK,
    AWAITING_PARAMETER_VALUE_OF_STRENGTH,
    AWAITING_PARAMETER_VALUE_OF_DEXTERITY,
    AWAITING_PARAMETER_VALUE_OF_CONSTITUTION,
    AWAITING_PARAMETER_VALUE_OF_DEFENSE,
    AWAITING_PARAMETER_VALUE_OF_MARKSMANSHIP,
    AWAITING_PARAMETER_VALUE_OF_SORCERY,
    AWAITING_PARAMETER_VALUE_OF_SPIRITUALITY,
    ;

    public static UserState of(String state) {
        return Arrays.stream(UserState.values())
            .filter(s -> s.name().equals(state.toUpperCase()))
            .findFirst().orElseThrow();
    }

    public static UserState ofParam(String param) {
        return of("AWAITING_PARAMETER_VALUE_OF_" + param.toUpperCase());
    }
}
