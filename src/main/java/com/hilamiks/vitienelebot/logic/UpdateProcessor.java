package com.hilamiks.vitienelebot.logic;

import com.hilamiks.vitienelebot.logic.processorContract.CallbackProcessingStrategy;
import com.hilamiks.vitienelebot.logic.processorContract.MessageProcessingStrategy;
import com.hilamiks.vitienelebot.service.BotUserService;
import com.hilamiks.vitienelebot.telegram.menu.Menu;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import com.hilamiks.vitienelebot.telegram.user.UserState;
import com.hilamiks.vitienelebot.utils.telegram.UpdateUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UpdateProcessor {

    private final Map<UserState, MessageProcessingStrategy> messageProcessingStrategies;
    private final Map<CallbackConstant, CallbackProcessingStrategy> callbackProcessingStrategies;
    private final BotUserService botUserService;

    UpdateProcessor(List<MessageProcessingStrategy> messageProcessingStrategyList,
                    List<CallbackProcessingStrategy> callbackProcessingStrategyList,
                    BotUserService botUserService) {
        messageProcessingStrategies = new HashMap<>();
        callbackProcessingStrategies = new HashMap<>();
        messageProcessingStrategyList.forEach(
            strategy -> {
                messageProcessingStrategies.put(strategy.getCorrelatingUserState(), strategy);
            }
        );
        callbackProcessingStrategyList.forEach(
            strategy -> {
                callbackProcessingStrategies.put(strategy.getCorrelatingCallbackData(), strategy);
            }
        );
        this.botUserService = botUserService;
    }

    public BotApiMethod process(Update update) {
        String chatId = UpdateUtils.extractChatId(update);
        BotUser user = botUserService.findOrCreateBotUser(chatId);
        try {

            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                MessageProcessingStrategy messageProcessingStrategy = messageProcessingStrategies.get(
                    user.getUserState()
                );

                if (messageProcessingStrategy != null) {
                    return messageProcessingStrategy.process(user, update, messageText);
                }
            }

            if (update.hasCallbackQuery()) {
                String callBackQueryText = update.getCallbackQuery().getData();
                String[] split = callBackQueryText.split("\\?");
                String query = split[0];
                System.out.println("TRYING TO FIND PROCESSOR FOR QUERY: " + query);
                String param = null;
                if (split.length > 1) {
                    param = split[1];
                }
                CallbackProcessingStrategy callbackProcessingStrategy = callbackProcessingStrategies.get(CallbackConstant.of(query));
                System.out.println(callbackProcessingStrategy.getClass().getSimpleName() + " was determined");
                if (callbackProcessingStrategy != null) {
                    return callbackProcessingStrategy.process(user, update, param);
                }
            }

            throw new RuntimeException("Неизвестная команда!");
        } catch (Exception e) {
            e.printStackTrace();
            if (update.hasCallbackQuery()) {
                return EditMessageText.builder()
                    .text("Команда неизвестна! Пожалуйста выберите действие: ")
                    .messageId(update.getCallbackQuery().getMessage().getMessageId())
                    .chatId("" + user.getTelegramId())
                    .replyMarkup(Menu.getBaseMenu(user))
                    .build();
            }
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Команда неизвестна! Пожалуйста выберите действие:");
            sendMessage.setChatId(
                chatId);
            sendMessage.setReplyMarkup(Menu.getBaseMenu(user));
            return sendMessage;
        }
    }

}
