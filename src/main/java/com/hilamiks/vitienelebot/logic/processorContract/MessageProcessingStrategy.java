package com.hilamiks.vitienelebot.logic.processorContract;

import com.hilamiks.vitienelebot.telegram.user.BotUser;
import com.hilamiks.vitienelebot.telegram.user.UserState;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageProcessingStrategy {

    BotApiMethod process(BotUser user, Update update, String param);

    UserState getCorrelatingUserState();

}
