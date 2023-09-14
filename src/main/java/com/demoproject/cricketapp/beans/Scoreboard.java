package com.demoproject.cricketapp.beans;

import com.demoproject.cricketapp.commons.enums.TossChoice;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Scoreboard {
    private String id;


    private Team team1; // Use @DBRef to store a reference to the Team document
    private Team team2; // Use @DBRef to store a reference to the Team document

    private String tossWonByTeamId;
    private TossChoice tossChoice;
}
