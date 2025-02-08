package com.hilamiks.vitienelebot.logic.callbackProcessors;

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
public class ChangeParameterCallbackProcessorStrategy implements CallbackProcessingStrategy {

    @Autowired
    private BotUserService userService;

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        System.out.println("TRYING TO SET USER STATE TO PARAM: " + param);
        userService.updateUserStateFor(user.getTelegramId(), UserState.ofParam(param));
        return EditMessageText.builder()
            .text("Отправьте ОДНО число чтобы выставить натуральное значение параметра:")
            .chatId("" + user.getTelegramId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.CHANGE_PARAMETER;
    }
}
