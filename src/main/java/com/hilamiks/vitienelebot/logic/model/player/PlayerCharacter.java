package com.hilamiks.vitienelebot.logic.model.player;

import com.hilamiks.vitienelebot.logic.model.game.ParameterType;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class PlayerCharacter {
    private final String id = UUID.randomUUID().toString();
    private StaticCharacteristics staticCharacteristics;
    private Map<ParameterType, Integer> stats;
    private String imagePath;

    @Data
    public static class StaticCharacteristics {
        private String name;
        private PlayerRace race;
        private PlayerClass playerClass;
        private PlayerGrowthRate growthRate;
    }
}
