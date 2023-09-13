package com.demoproject.cricketapp.commons.enums;

public enum TossChoice {
    BAT("Bat"),
    BOWL("Bowl");
    private final String choice;

    TossChoice(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }
}
