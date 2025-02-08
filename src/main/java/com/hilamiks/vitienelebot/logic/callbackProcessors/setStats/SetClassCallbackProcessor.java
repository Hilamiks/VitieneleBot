package com.hilamiks.vitienelebot.logic.callbackProcessors.setStats;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.model.player.PlayerClass;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SetClassCallbackProcessor implements CallbackProcessingStrategy {
    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        PlayerClass playerClass = PlayerClass.valueOf(param);
        user.getSelectedCharacter().getStaticCharacteristics().setPlayerClass(
            playerClass
        );
        return EditMessageText.builder()
            .chatId("" + user.getTelegramId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .text("Ваш персонаж теперь " + playerClass.getCyrillicName())
            .replyMarkup(Menu.getBaseMenu(user))
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.SET_CLASS;
    }
}
