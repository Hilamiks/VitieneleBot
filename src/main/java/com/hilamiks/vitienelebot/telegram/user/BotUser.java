package com.hilamiks.vitienelebot.telegram.user;


import com.hilamiks.vitienelebot.logic.model.player.PlayerCharacter;
import lombok.Data;

import java.util.Map;

@Data
public class BotUser {

    private long telegramId;
    private Map<String, PlayerCharacter> characterList;
    private PlayerCharacter selectedCharacter;
    private UserState userState;

}
