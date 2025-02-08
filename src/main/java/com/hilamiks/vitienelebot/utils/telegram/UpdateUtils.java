package com.hilamiks.vitienelebot.utils.telegram;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@UtilityClass
public class UpdateUtils {

    public static String extractChatId(Update update) {
        if (update.hasMessage()) {
            return "" + update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            return "" + update.getCallbackQuery().getFrom().getId();
        }
        return "" + update.getEditedMessage().getChatId();
    }

    public static BotApiMethod basicErrorMessage(Update update, TelegramApiException e) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(extractChatId(update));
        sendMessage.setText("Произошла ошибка: " + e.getMessage());
        return sendMessage;
    }
}
