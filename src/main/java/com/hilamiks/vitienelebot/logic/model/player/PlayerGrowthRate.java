package com.hilamiks.vitienelebot.logic.model.player;

public enum PlayerGrowthRate {
    VERY_FAST(50),
    FAST(100),
    MEDIUM(150),
    SLOW(200),
    VERY_SLOW(300);

    int experienceThreshold;

    PlayerGrowthRate(int experienceThreshold) {
        this.experienceThreshold = experienceThreshold;
    }
}
