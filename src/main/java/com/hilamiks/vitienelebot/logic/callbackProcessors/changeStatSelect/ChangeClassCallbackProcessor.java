package com.hilamiks.vitienelebot.logic.callbackProcessors.changeStatSelect;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ChangeClassCallbackProcessor implements CallbackProcessingStrategy {
    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        return EditMessageText.builder()
            .chatId("" + user.getTelegramId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .text("Выберите класс:")
            .replyMarkup(Menu.getClassList(user))
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.CHANGE_CLASS;
    }
}
