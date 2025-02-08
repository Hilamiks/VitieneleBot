package com.hilamiks.vitienelebot.service;

import com.hilamiks.vitienelebot.persistence.UserRepository;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import com.hilamiks.vitienelebot.telegram.user.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BotUserService {

    private final UserRepository userRepository;

    public BotUser findOrCreateBotUser(String chatId) {
        return userRepository.findByTelegramId(
            Long.parseLong(chatId)
        ).orElseGet(
            () -> {
                BotUser user = new BotUser();
                user.setTelegramId(Long.parseLong(chatId));
                user.setUserState(UserState.BASE);
                user.setCharacterList(new HashMap<>());
                user.setSelectedCharacter(null);
                return userRepository.save(user);
            }
        );
    }

    public void updateUserStateFor(long telegramId, UserState userState) {
        userRepository.findByTelegramId(telegramId).ifPresent(user -> {
            user.setUserState(userState);
        });
    }
}
