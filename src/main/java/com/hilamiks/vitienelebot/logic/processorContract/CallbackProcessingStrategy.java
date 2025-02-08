package com.hilamiks.vitienelebot.logic.processorContract;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackProcessingStrategy {

    BotApiMethod process(BotUser user, Update update, String param);

    CallbackConstant getCorrelatingCallbackData();

}
