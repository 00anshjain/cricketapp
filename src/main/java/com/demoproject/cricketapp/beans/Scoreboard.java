package com.demoproject.cricketapp.beans;

import com.demoproject.cricketapp.commons.enums.TossChoice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Scoreboard {
    private String id;


    private Team team1; // Use @DBRef to store a reference to the Team document
    private Team team2; // Use @DBRef to store a reference to the Team document

    private String tossWonByTeamId;
    private TossChoice tossChoice;
}
