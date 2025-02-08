package com.hilamiks.vitienelebot.logic.model.player;
import com.hilamiks.vitienelebot.logic.model.game.ParameterType;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

@Getter
public enum PlayerClass {
    WARRIOR(Pair.of(ParameterType.ATTACK, ParameterType.STRENGTH), ParameterType.DEXTERITY, "Воин"),
    BERSERK(Pair.of(ParameterType.ATTACK, ParameterType.STRENGTH), ParameterType.CONSTITUTION, "Берсерк"),
    ATHLETE(Pair.of(ParameterType.ATTACK, ParameterType.DEXTERITY), ParameterType.STRENGTH, "Атлет"),
    MARKSMAN(Pair.of(ParameterType.MARKSMANSHIP, ParameterType.DEXTERITY), ParameterType.ATTACK, "Стрелок"),
    KNIGHT(Pair.of(ParameterType.ATTACK, ParameterType.DEFENSE), ParameterType.STRENGTH, "Рыцарь"),
    DEFENDER(Pair.of(ParameterType.ATTACK, ParameterType.DEFENSE), ParameterType.DEXTERITY, "Защитник");

    private Pair<ParameterType, ParameterType> primaryParameters;
    private ParameterType secondaryParameter;
    private String cyrillicName;

    PlayerClass(
        Pair<ParameterType, ParameterType> primaryParameters,
        ParameterType secondaryParameter,
        String cyrillicName) {
        this.primaryParameters = primaryParameters;
        this.secondaryParameter = secondaryParameter;
        this.cyrillicName = cyrillicName;
    }
}