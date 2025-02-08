package com.hilamiks.vitienelebot.telegram.menu;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.model.player.PlayerCharacter;
import com.hilamiks.vitienelebot.logic.model.player.PlayerClass;
import com.hilamiks.vitienelebot.logic.model.player.PlayerRace;
import com.hilamiks.vitienelebot.telegram.user.BotUser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.hilamiks.vitienelebot.telegram.menu.ButtonTemplates.*;

public class Menu {

    public static InlineKeyboardMarkup getBaseMenu(BotUser user) {
        return InlineKeyboardMarkup.builder()
            .keyboard(
                List.of(
                    List.of(
                        InlineKeyboardButton.builder()
                            .text("Текущий персонаж: " +
                                getCharName(user))
                            .callbackData(CallbackConstant.VIEW_CHARACTER.name())
                            .build()
                    ),
                    List.of(
                        InlineKeyboardButton.builder()
                            .text("Создать нового персонажа")
                            .callbackData(CallbackConstant.NEW_CHAR.name())
                            .build()
                    ),
                    List.of(
                        InlineKeyboardButton.builder()
                            .text("Мои персонажи")
                            .callbackData(CallbackConstant.LIST_CHARS.name())
                            .build()
                    )
                )
            )
            .build();
    }

    private static String getCharName(BotUser user) {
        if (user.getSelectedCharacter() == null) {
            return "персонаж не выбран";
        }
        return user.getSelectedCharacter().getStaticCharacteristics().getName();
    }

    public static InlineKeyboardMarkup viewCharacterMenu(PlayerCharacter playerCharacter) {
        return InlineKeyboardMarkup.builder()
            .keyboard(
                playerCharacterToKeyboard(playerCharacter)
            )
            .build();
    }

    private static Collection<? extends List<InlineKeyboardButton>> playerCharacterToKeyboard(
        PlayerCharacter playerCharacter) {
        return List.of(
            List.of(
                getNameButton(playerCharacter)
            ),
            List.of(
                getRaceButton(playerCharacter)
            ),
            List.of(
                getClassButton(playerCharacter)
            ),
            List.of(
                addAttackButton(playerCharacter), addStrengthButton(playerCharacter)
            ),
            List.of(
                addDexterityButton(playerCharacter), addMarksmanshipButton(playerCharacter)
            ),
            List.of(
                addConstitutionButton(playerCharacter), addDefenseButton(playerCharacter)
            ),
            List.of(
                addSorceryButton(playerCharacter), addSpiritualityButton(playerCharacter)
            ),
            List.of(
                saveCharacterButton(playerCharacter)
            ),
            List.of(
                discardCharacterButton(playerCharacter)
            )
        );
    }

    public static InlineKeyboardMarkup getCharactersList(BotUser user) {
        return InlineKeyboardMarkup.builder()
            .keyboard(
                getAllCharactersKeyboard(user)
            )
            .build();
    }

    private static Collection<? extends List<InlineKeyboardButton>> getAllCharactersKeyboard(BotUser user) {
        List<List<InlineKeyboardButton>> buttons = user.getCharacterList().entrySet()
            .stream().map(
                entry -> List.of(
                    InlineKeyboardButton.builder()
                        .text(entry.getValue().getStaticCharacteristics().getName())
                        .callbackData(CallbackConstant.SET_SELECTED_CHARACTER.name() + "?" + entry.getValue().getId())
                        .build(),
                    InlineKeyboardButton.builder()
                        .text("❌")
                        .callbackData(CallbackConstant.DELETE_CHARACTER.name() + "?" + entry.getValue().getId())
                        .build()
                )
            ).toList();
        var result = new ArrayList<List<InlineKeyboardButton>>(buttons);
        result.add(List.of(getHomeButton()));
        return result;
    }

    public static InlineKeyboardMarkup getRaceList(BotUser user) {
        List<List<InlineKeyboardButton>> buttons =  Arrays.stream(PlayerRace.values())
            .map(
                race -> List.of(
                    InlineKeyboardButton.builder()
                        .text(race.getCyrillicName())
                        .callbackData(CallbackConstant.SET_RACE + "?" + race.getCommonName())
                        .build()
                )
            ).toList();
        var result = new ArrayList<List<InlineKeyboardButton>>(buttons);
        result.add(List.of(getHomeButton()));
        return InlineKeyboardMarkup.builder()
            .keyboard(
                result
            )
            .build();
    }

    public static InlineKeyboardMarkup getClassList(BotUser user) {
        List<List<InlineKeyboardButton>> buttons =  Arrays.stream(PlayerClass.values())
            .map(
                race -> List.of(
                    InlineKeyboardButton.builder()
                        .text(race.getCyrillicName())
                        .callbackData(CallbackConstant.SET_CLASS + "?" + race.name())
                        .build()
                )
            ).toList();
        var result = new ArrayList<List<InlineKeyboardButton>>(buttons);
        result.add(List.of(getHomeButton()));
        return InlineKeyboardMarkup.builder()
            .keyboard(
                result
            )
            .build();
    }
}
