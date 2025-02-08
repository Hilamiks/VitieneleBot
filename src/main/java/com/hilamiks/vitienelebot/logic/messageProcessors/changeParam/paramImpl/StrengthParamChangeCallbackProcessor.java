package com.hilamiks.vitienelebot.logic.messageProcessors.changeParam.paramImpl;

import com.hilamiks.vitienelebot.logic.messageProcessors.changeParam.ParamChangeCallbackProcessor;
import com.hilamiks.vitienelebot.logic.model.game.ParameterType;
import com.hilamiks.vitienelebot.telegram.user.UserState;
import org.springframework.stereotype.Component;

@Component
public class StrengthParamChangeCallbackProcessor extends ParamChangeCallbackProcessor {

    @Override
    protected ParameterType getModifiedParam() {
        return ParameterType.STRENGTH;
    }

    @Override
    public UserState getCorrelatingUserState() {
        return UserState.AWAITING_PARAMETER_VALUE_OF_STRENGTH;
    }

}
