package com.demoproject.cricketapp.commons.enums;

import lombok.Getter;

@Getter
public enum TossChoice {
    BAT("Bat"),
    BOWL("Bowl");
    private final String choice;

    TossChoice(String choice) {
        this.choice = choice;
    }

}
