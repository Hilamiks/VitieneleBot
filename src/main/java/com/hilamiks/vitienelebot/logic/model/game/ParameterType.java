package com.hilamiks.vitienelebot.logic.model.game;

import lombok.Getter;

@Getter
public enum ParameterType {
    ATTACK("Атака"),
    STRENGTH("Сила"),
    DEXTERITY("Ловкость"),
    CONSTITUTION("Живучесть"),
    DEFENSE("Защита"),
    MARKSMANSHIP("Стрельба"),
    SORCERY("Чары"),
    SPIRITUALITY("Духовность");

    private String cyrillicName;

    ParameterType(String cyrillicName) {
        this.cyrillicName = cyrillicName;
    }

}