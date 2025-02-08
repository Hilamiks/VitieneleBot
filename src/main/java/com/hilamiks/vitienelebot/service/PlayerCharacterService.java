package com.hilamiks.vitienelebot.service;

import com.hilamiks.vitienelebot.logic.model.game.ParameterType;
import com.hilamiks.vitienelebot.logic.model.player.PlayerCharacter;
import com.hilamiks.vitienelebot.logic.model.player.PlayerClass;
import com.hilamiks.vitienelebot.logic.model.player.PlayerGrowthRate;
import com.hilamiks.vitienelebot.logic.model.player.PlayerRace;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerCharacterService {

    private final FakerService fakerService;

    public PlayerCharacterService(FakerService fakerService) {
        this.fakerService = fakerService;
    }

    public PlayerCharacter generatePlayerCharacter() {
        PlayerCharacter playerCharacter = new PlayerCharacter();
        playerCharacter.setStaticCharacteristics(generateBaseCharacteristics());
        playerCharacter.setStats(generateStartingStats(playerCharacter.getStaticCharacteristics()));
        return playerCharacter;
    }

    private Map<ParameterType, Integer> generateStartingStats(
        PlayerCharacter.StaticCharacteristics staticCharacteristics) {
        PlayerRace playerRace = staticCharacteristics.getRace();
        Map<ParameterType, Integer> startingStats = new HashMap<>();

        playerRace.getBaseValues().forEach((parameterType, value) -> {
            Integer minVal = value.getLeft();
            startingStats.put(parameterType, minVal);
        });

        return startingStats;
    }

    private PlayerCharacter.StaticCharacteristics generateBaseCharacteristics() {
        PlayerCharacter.StaticCharacteristics staticCharacteristics =
            new PlayerCharacter.StaticCharacteristics();
        staticCharacteristics.setGrowthRate(PlayerGrowthRate.MEDIUM);
        staticCharacteristics.setName(fakerService.getFakeName());
        staticCharacteristics.setPlayerClass(PlayerClass.WARRIOR);
        staticCharacteristics.setRace(PlayerRace.ADANS);
        return staticCharacteristics;
    }
}
