package com.hilamiks.vitienelebot.logic.callbackProcessors.changeStatSelect;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.service.BotUserService;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import com.hilamiks.vitienelebot.telegram.user.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ChangeNameCallbackProcessor implements CallbackProcessingStrategy {

    @Autowired
    private BotUserService userService;

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        userService.updateUserStateFor(user.getTelegramId(), UserState.AWAITING_NAME);
        return EditMessageText.builder()
            .text("Пожалуйста отправьте имя персонажа следующим сообщением: ")
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .chatId("" + user.getTelegramId())
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.CHANGE_NAME;
    }
}
