package com.demoproject.cricketapp.commons.enums;

public enum PlayerType {
    BATSMAN("batsman"),
    BOWLER("bowler"),
    ALLROUNDER("allrounder");

    public final String value;
    PlayerType(String value) {
        this.value =  value;
    }
    public String getValue() { return this.value; }
}
