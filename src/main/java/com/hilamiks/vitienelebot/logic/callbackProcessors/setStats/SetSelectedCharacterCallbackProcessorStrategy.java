package com.hilamiks.vitienelebot.logic.callbackProcessors.setStats;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.logic.model.player.PlayerCharacter;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SetSelectedCharacterCallbackProcessorStrategy implements CallbackProcessingStrategy {
    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        PlayerCharacter character = user.getCharacterList().get(param);

        if (character == null) {
            return EditMessageText.builder()
                .text("Персонаж не найден!")
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .chatId("" + user.getTelegramId())
                .replyMarkup(Menu.getBaseMenu(user))
                .build();
        }
        user.setSelectedCharacter(character);
        return EditMessageText.builder()
            .text("Персонаж " + character.getStaticCharacteristics().getName() + " выбран!")
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .chatId("" + user.getTelegramId())
            .replyMarkup(Menu.getBaseMenu(user))
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.SET_SELECTED_CHARACTER;
    }
}
