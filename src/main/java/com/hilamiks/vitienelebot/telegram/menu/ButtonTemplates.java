package com.hilamiks.vitienelebot.telegram.menu;

import com.hilamiks.vitienelebot.logic.CallbackConstant;
import com.hilamiks.vitienelebot.logic.model.game.ParameterType;
import com.hilamiks.vitienelebot.logic.model.player.PlayerCharacter;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@UtilityClass
public class ButtonTemplates {

    public static InlineKeyboardButton getHomeButton() {
        return InlineKeyboardButton.builder()
            .text("\uD83C\uDFE0")
            .callbackData(CallbackConstant.HOME.name())
            .build();
    }

    public static InlineKeyboardButton saveCharacterButton(PlayerCharacter playerCharacter) {
        return InlineKeyboardButton.builder()
            .text("Сохранить Персонажа")
            .callbackData(CallbackConstant.SAVE_CHARACTER.name() + "?" + playerCharacter.getId())
            .build();
    }

    public static InlineKeyboardButton discardCharacterButton(PlayerCharacter playerCharacter) {
        return InlineKeyboardButton.builder()
            .text("Сбросить Персонажа")
            .callbackData(CallbackConstant.DISCARD_CHARACTER.name() + "?" + playerCharacter.getId())
            .build();
    }

    public static InlineKeyboardButton getRaceButton(PlayerCharacter playerCharacter) {
        return InlineKeyboardButton.builder()
            .text("РАСА: " + playerCharacter.getStaticCharacteristics().getRace().getCyrillicName())
            .callbackData(CallbackConstant.CHANGE_RACE.name())
            .build();
    }

    public static InlineKeyboardButton getClassButton(PlayerCharacter playerCharacter) {
        return InlineKeyboardButton.builder()
            .text("КЛАСС: " + playerCharacter.getStaticCharacteristics().getPlayerClass().getCyrillicName())
            .callbackData(CallbackConstant.CHANGE_CLASS.name())
            .build();
    }

    public static InlineKeyboardButton getNameButton(PlayerCharacter playerCharacter) {
        return InlineKeyboardButton.builder()
            .text("ИМЯ: " + playerCharacter.getStaticCharacteristics().getName())
            .callbackData(CallbackConstant.CHANGE_NAME.name())
            .build();
    }

    public static InlineKeyboardButton getParameterButton(PlayerCharacter playerCharacter, ParameterType parameterType) {
        return InlineKeyboardButton.builder()
            .text(parameterType.getCyrillicName() + ": " + playerCharacter.getStats().get(parameterType))
            .callbackData(CallbackConstant.CHANGE_PARAMETER.name() + "?" + parameterType.name())
            .build();
    }

    public static InlineKeyboardButton addAttackButton(PlayerCharacter playerCharacter) {
        return getParameterButton(playerCharacter, ParameterType.ATTACK);
    }

    public static InlineKeyboardButton addStrengthButton(PlayerCharacter playerCharacter) {
        return getParameterButton(playerCharacter, ParameterType.STRENGTH);
    }

    public static InlineKeyboardButton addDefenseButton(PlayerCharacter playerCharacter) {
        return getParameterButton(playerCharacter, ParameterType.DEFENSE);
    }

    public static InlineKeyboardButton addMarksmanshipButton(PlayerCharacter playerCharacter) {
        return getParameterButton(playerCharacter, ParameterType.MARKSMANSHIP);
    }

    public static InlineKeyboardButton addDexterityButton(PlayerCharacter playerCharacter) {
        return getParameterButton(playerCharacter, ParameterType.DEXTERITY);
    }

    public static InlineKeyboardButton addConstitutionButton(PlayerCharacter playerCharacter) {
        return getParameterButton(playerCharacter, ParameterType.CONSTITUTION);
    }

    public static InlineKeyboardButton addSorceryButton(PlayerCharacter playerCharacter) {
        return getParameterButton(playerCharacter, ParameterType.SORCERY);
    }

    public static InlineKeyboardButton addSpiritualityButton(PlayerCharacter playerCharacter) {
        return getParameterButton(playerCharacter, ParameterType.SPIRITUALITY);
    }
}
