package com.hilamiks.vitienelebot.persistence.user;

import com.hilamiks.vitienelebot.persistence.UserRepository;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryUserRepository implements UserRepository {

    ConcurrentHashMap<Long, BotUser> users = new ConcurrentHashMap<>();

    @Override
    public BotUser save(BotUser botUser) {
        users.put(botUser.getTelegramId(), botUser);
        return botUser;
    }

    @Override
    public Optional<BotUser> findByTelegramId(Long telegramId) {
        return Optional.ofNullable(users.get(telegramId));
    }
}
