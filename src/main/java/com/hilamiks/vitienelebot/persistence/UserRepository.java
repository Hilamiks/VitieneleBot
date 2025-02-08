package com.hilamiks.vitienelebot.persistence;

import com.hilamiks.vitienelebot.telegram.user.BotUser;

import java.util.Optional;

public interface UserRepository {

    BotUser save(BotUser botUser);
    Optional<BotUser> findByTelegramId(Long telegramId);

}
