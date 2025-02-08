package com.hilamiks.vitienelebot.logic.callbackProcessors;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.model.player.PlayerCharacter;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import com.hilamiks.vitienelebot.utils.context.ApplicationContextProvider;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ViewSelectedCharacterCallbackProcessorStrategy implements CallbackProcessingStrategy {

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        PlayerCharacter selectedCharacter = user.getSelectedCharacter();
        if (selectedCharacter == null) {
            return ApplicationContextProvider.getBean(NewCharCallbackProcessor.class)
                .process(user, update, param);
        }

        return EditMessageText.builder()
            .chatId("" + user.getTelegramId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .text("Информация о " + selectedCharacter.getStaticCharacteristics().getName())
            .replyMarkup(Menu.viewCharacterMenu(selectedCharacter))
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.VIEW_CHARACTER;
    }
}
