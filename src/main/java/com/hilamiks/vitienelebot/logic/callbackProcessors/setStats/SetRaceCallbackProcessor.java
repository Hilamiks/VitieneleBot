package com.hilamiks.vitienelebot.logic.callbackProcessors.setStats;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.model.player.PlayerRace;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SetRaceCallbackProcessor implements CallbackProcessingStrategy {
    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        PlayerRace race = PlayerRace.ofCommonName(param);
        user.getSelectedCharacter().getStaticCharacteristics().setRace(
            race
        );
        return EditMessageText.builder()
            .chatId("" + user.getTelegramId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .text("Ваш персонаж теперь " + race.getCyrillicName())
            .replyMarkup(Menu.getBaseMenu(user))
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.SET_RACE;
    }
}
