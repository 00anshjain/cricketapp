package com.demoproject.cricketapp.beans.response;

import com.demoproject.cricketapp.commons.enums.PlayerType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerInfoResponse {
    private String id;
    private String name;
    private int age;
    private int numberOfMatches;
    private int totalRuns;
    private int totalWickets;
    private PlayerType playerType;
    private int battingSkill;
    private int bowlingSkill;
}
