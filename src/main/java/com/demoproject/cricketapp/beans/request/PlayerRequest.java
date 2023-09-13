package com.demoproject.cricketapp.beans.request;

import com.demoproject.cricketapp.commons.enums.PlayerType;
import lombok.Data;

@Data
public class PlayerRequest {

    private String name;
    private int age;
    private PlayerType playerType;
    private int battingSkill;
    private int bowlingSkill;
}
