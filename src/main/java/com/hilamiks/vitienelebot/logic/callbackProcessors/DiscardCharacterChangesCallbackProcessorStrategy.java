package com.hilamiks.vitienelebot.logic.callbackProcessors;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class DiscardCharacterChangesCallbackProcessorStrategy implements CallbackProcessingStrategy {

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        user.setSelectedCharacter(null);
        return EditMessageText.builder()
            .text("Изменения сброшены!")
            .chatId("" + user.getTelegramId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .replyMarkup(Menu.getBaseMenu(user))
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.DISCARD_CHARACTER;
    }
}
