package com.hilamiks.vitienelebot.logic.model.player;


import com.hilamiks.vitienelebot.logic.model.game.ParameterType;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Map;

@Getter
public enum PlayerRace {
    ADANS("human", Map.of(
        ParameterType.ATTACK, Pair.of(9, 120),
        ParameterType.STRENGTH, Pair.of(9, 120),
        ParameterType.MARKSMANSHIP, Pair.of(9, 120),
        ParameterType.DEXTERITY, Pair.of(9, 100),
        ParameterType.DEFENSE, Pair.of(9, 100),
        ParameterType.CONSTITUTION, Pair.of(4, 100),
        ParameterType.SORCERY, Pair.of(4, 120),
        ParameterType.SPIRITUALITY, Pair.of(4, 120)
    ), 10, "Адан"),
    QUENDI("elf",Map.of(
        ParameterType.ATTACK, Pair.of(4, 120),
        ParameterType.STRENGTH, Pair.of(4, 100),
        ParameterType.MARKSMANSHIP, Pair.of(14, 120),
        ParameterType.DEXTERITY, Pair.of(15, 160),
        ParameterType.DEFENSE, Pair.of(4, 100),
        ParameterType.CONSTITUTION, Pair.of(4, 100),
        ParameterType.SORCERY, Pair.of(6, 140),
        ParameterType.SPIRITUALITY, Pair.of(6, 140)
    ), 12, "Квенди"),
    PERIANS("hobbits",Map.of(
        ParameterType.ATTACK, Pair.of(2, 120),
        ParameterType.STRENGTH, Pair.of(2, 80),
        ParameterType.MARKSMANSHIP, Pair.of(13, 120),
        ParameterType.DEXTERITY, Pair.of(13, 120),
        ParameterType.DEFENSE, Pair.of(2, 100),
        ParameterType.CONSTITUTION, Pair.of(2, 80),
        ParameterType.SORCERY, Pair.of(4, 100),
        ParameterType.SPIRITUALITY, Pair.of(4, 100)
    ), 11, "Периан"),
    CASARS("dwarf",Map.of(
        ParameterType.ATTACK, Pair.of(14, 120),
        ParameterType.STRENGTH, Pair.of(14, 140),
        ParameterType.MARKSMANSHIP, Pair.of(2, 120),
        ParameterType.DEXTERITY, Pair.of(2, 80),
        ParameterType.DEFENSE, Pair.of(13, 100),
        ParameterType.CONSTITUTION, Pair.of(8, 100),
        ParameterType.SORCERY, Pair.of(2, 80),
        ParameterType.SPIRITUALITY, Pair.of(2, 80)
    ), 9, "Касар"),
    LYRCHES("reptiloid",Map.of(
        ParameterType.ATTACK, Pair.of(3, 120),
        ParameterType.STRENGTH, Pair.of(3, 100),
        ParameterType.MARKSMANSHIP, Pair.of(11, 120),
        ParameterType.DEXTERITY, Pair.of(12, 140),
        ParameterType.DEFENSE, Pair.of(19, 100),
        ParameterType.CONSTITUTION, Pair.of(3, 100),
        ParameterType.SORCERY, Pair.of(2, 80),
        ParameterType.SPIRITUALITY, Pair.of(4, 120)
    ), 12, "Лирч"),
    URUKS("half-orc",Map.of(
        ParameterType.ATTACK, Pair.of(14, 120),
        ParameterType.STRENGTH, Pair.of(6, 120),
        ParameterType.MARKSMANSHIP, Pair.of(10, 120),
        ParameterType.DEXTERITY, Pair.of(11, 120),
        ParameterType.DEFENSE, Pair.of(6, 100),
        ParameterType.CONSTITUTION, Pair.of(4, 100),
        ParameterType.SORCERY, Pair.of(3, 100),
        ParameterType.SPIRITUALITY, Pair.of(3, 100)
    ), 11, "Урук"),
    OLOGS("half-troll",Map.of(
        ParameterType.ATTACK, Pair.of(19, 120),
        ParameterType.STRENGTH, Pair.of(19, 160),
        ParameterType.MARKSMANSHIP, Pair.of(1, 120),
        ParameterType.DEXTERITY, Pair.of(1, 60),
        ParameterType.DEFENSE, Pair.of(7, 100),
        ParameterType.CONSTITUTION, Pair.of(10, 120),
        ParameterType.SORCERY, Pair.of(0, 40),
        ParameterType.SPIRITUALITY, Pair.of(0, 40)
    ), 8, "Олог"),
    SILMS("half-drow",Map.of(
        ParameterType.ATTACK, Pair.of(2, 120),
        ParameterType.STRENGTH, Pair.of(2, 80),
        ParameterType.MARKSMANSHIP, Pair.of(7, 120),
        ParameterType.DEXTERITY, Pair.of(12, 140),
        ParameterType.DEFENSE, Pair.of(2, 100),
        ParameterType.CONSTITUTION, Pair.of(4, 100),
        ParameterType.SORCERY, Pair.of(14, 160),
        ParameterType.SPIRITUALITY, Pair.of(14, 160)
    ), 12, "Сильм");

    String commonName;
    Map<ParameterType, Pair<Integer, Integer>> baseValues;
    int baseSpeed;
    String cyrillicName;

    PlayerRace(String commonName,
               Map<ParameterType, Pair<Integer, Integer>> baseValues,
               int baseSpeed,
               String cyrillicName) {
        this.commonName = commonName;
        this.baseValues = baseValues;
        this.baseSpeed = baseSpeed;
        this.cyrillicName = cyrillicName;
    }

    public static PlayerRace ofCommonName(String commonName) {
        return Arrays.stream(PlayerRace.values())
            .filter(
                r -> r.getCommonName().equals(commonName)
            ).findFirst().orElse(null);
    }
}