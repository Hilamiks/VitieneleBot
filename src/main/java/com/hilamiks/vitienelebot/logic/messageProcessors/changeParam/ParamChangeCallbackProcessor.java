package com.hilamiks.vitienelebot.logic.messageProcessors.changeParam;

import com.hilamiks.vitienelebot.logic.model.game.ParameterType;
import com.hilamiks.vitienelebot.logic.processorContract.MessageProcessingStrategy;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class ParamChangeCallbackProcessor implements MessageProcessingStrategy {

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        Integer newParamValue = Integer.parseInt(param);
        user.getSelectedCharacter().getStats().put(getModifiedParam(), newParamValue);
        return SendMessage.builder()
            .chatId("" + user.getTelegramId())
            .text(getModifiedParam().getCyrillicName() + " теперь имеет значение " + newParamValue)
            .replyMarkup(Menu.getBaseMenu(user))
            .build();
    }

    protected abstract ParameterType getModifiedParam();

}
