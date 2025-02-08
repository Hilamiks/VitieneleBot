package com.hilamiks.vitienelebot.logic.callbackProcessors;

import com.hilamiks.vitienelebot.logic.processorContract.MessageProcessingStrategy;
import com.hilamiks.vitienelebot.service.BotUserService;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import com.hilamiks.vitienelebot.telegram.user.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ChangePlayerCharacterNameProcessor implements MessageProcessingStrategy {

    @Autowired
    private BotUserService botUserService;

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        botUserService.updateUserStateFor(user.getTelegramId(), UserState.BASE);
        String name = update.getMessage().getText();
        user.getSelectedCharacter().getStaticCharacteristics().setName(update.getMessage().getText());
        return SendMessage.builder()
            .chatId("" + update.getMessage().getChatId())
            .text("Вашего персонажа теперь зовут " + name)
            .replyMarkup(Menu.getBaseMenu(user))
            .build();
    }

    @Override
    public UserState getCorrelatingUserState() {
        return UserState.AWAITING_NAME;
    }
}
