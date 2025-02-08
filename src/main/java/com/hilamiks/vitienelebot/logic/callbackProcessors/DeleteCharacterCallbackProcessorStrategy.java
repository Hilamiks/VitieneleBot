package com.hilamiks.vitienelebot.logic.callbackProcessors;

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
public class DeleteCharacterCallbackProcessorStrategy implements CallbackProcessingStrategy {

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        PlayerCharacter playerCharacter = user.getCharacterList().get(param);
        if (playerCharacter == null) {
            return EditMessageText.builder()
                .chatId("" + user.getTelegramId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(Menu.getBaseMenu(user))
                .text("Персонаж не найден!")
                .build();
        }
        if (user.getSelectedCharacter() != null && user.getSelectedCharacter().getId().equals(param)) {
            user.setSelectedCharacter(null);
        }
        user.getCharacterList().remove(param);
        return EditMessageText.builder()
            .text("Персонаж " + playerCharacter.getStaticCharacteristics().getName() + " удалён!")
            .replyMarkup(Menu.getBaseMenu(user))
            .chatId("" + user.getTelegramId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.DELETE_CHARACTER;
    }

}
