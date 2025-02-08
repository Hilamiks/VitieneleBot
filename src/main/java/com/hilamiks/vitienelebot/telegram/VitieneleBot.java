package com.hilamiks.vitienelebot.telegram;

import com.hilamiks.vitienelebot.logic.UpdateProcessor;
import com.hilamiks.vitienelebot.utils.telegram.UpdateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class VitieneleBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.token}")
    private String TOKEN;
    @Value("${telegram.bot.username}")
    private String BOT_USERNAME;

    private final UpdateProcessor updateProcessor;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        executor.submit(() -> {
            try {
                execute(updateProcessor.process(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
                try {
                    execute(UpdateUtils.basicErrorMessage(update, e));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
