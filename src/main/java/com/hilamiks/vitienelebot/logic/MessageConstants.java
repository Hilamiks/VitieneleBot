package com.hilamiks.vitienelebot.logic;

import lombok.Getter;

@Getter
public enum MessageConstants {
    START("/start");

    private final String content;

    MessageConstants(String content) {
        this.content = content;
    }

    public boolean equals(String message) {
        return this.content.equals(message);
    }
}
