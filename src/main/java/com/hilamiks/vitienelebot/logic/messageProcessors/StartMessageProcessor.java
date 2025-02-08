package com.hilamiks.vitienelebot.logic.messageProcessors;

import com.hilamiks.vitienelebot.logic.processorContract.MessageProcessingStrategy;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import com.hilamiks.vitienelebot.telegram.user.UserState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartMessageProcessor implements MessageProcessingStrategy {


    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        return SendMessage.builder()
            .chatId("" + update.getMessage().getChatId())
            .text("Добро пожаловать в Витэнель бот!!!")
            .replyMarkup(Menu.getBaseMenu(user))
            .build();
    }

    @Override
    public UserState getCorrelatingUserState() {
        return UserState.BASE;
    }
}
