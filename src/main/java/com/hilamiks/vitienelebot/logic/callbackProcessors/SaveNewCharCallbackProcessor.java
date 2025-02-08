package com.hilamiks.vitienelebot.logic.callbackProcessors;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.logic.model.player.PlayerCharacter;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class SaveNewCharCallbackProcessor implements CallbackProcessingStrategy {

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        PlayerCharacter playerCharacter = user.getSelectedCharacter();

        if (playerCharacter == null) {
            return EditMessageText.builder()
                .chatId("" + user.getTelegramId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .text("Ошибка: Персонаж не найден!")
                .replyMarkup(Menu.getBaseMenu(user))
                .build();
        }

        user.getCharacterList().put(playerCharacter.getId(), playerCharacter);
        user.setSelectedCharacter(playerCharacter);

        return EditMessageText.builder()
            .chatId("" + user.getTelegramId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .text("Персонаж успешно сохранён!")
            .replyMarkup(Menu.getBaseMenu(user))
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.SAVE_CHARACTER;
    }

}
