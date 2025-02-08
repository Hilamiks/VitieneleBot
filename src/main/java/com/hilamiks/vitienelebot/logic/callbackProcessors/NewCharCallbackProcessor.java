package com.hilamiks.vitienelebot.logic.callbackProcessors;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.logic.model.player.PlayerCharacter;
import com.hilamiks.vitienelebot.service.PlayerCharacterService;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class NewCharCallbackProcessor implements CallbackProcessingStrategy {

    private final PlayerCharacterService playerCharacterService;

    @Override
    public BotApiMethod process(BotUser user, Update update, String param) {
        PlayerCharacter playerCharacter = playerCharacterService.generatePlayerCharacter();
        user.getCharacterList().put(playerCharacter.getId(), playerCharacter);
        user.setSelectedCharacter(playerCharacter);

        return EditMessageText.builder()
            .chatId("" + update.getCallbackQuery().getFrom().getId())
            .messageId(update.getCallbackQuery().getMessage().getMessageId())
            .replyMarkup(Menu.viewCharacterMenu(playerCharacter))
            .text("Новый персонаж создан. Вы можете менять его перед тем как сохранить!")
            .build();
    }

    @Override
    public CallbackConstant getCorrelatingCallbackData() {
        return CallbackConstant.NEW_CHAR;
    }
}
