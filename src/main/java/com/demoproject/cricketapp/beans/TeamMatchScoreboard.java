package com.demoproject.cricketapp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamMatchScoreboard {

    private String id;
    private String matchId;
    private String scoreboardID;
    private String teamID;
    private String teamName;
    private int teamScore;
    private int ballsBowled;
    private int wicket;

    private List<Player> playerScores;
}
