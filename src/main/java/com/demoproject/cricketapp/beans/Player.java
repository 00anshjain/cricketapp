package com.demoproject.cricketapp.beans;

import com.demoproject.cricketapp.commons.enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("players")
public class Player {
    @Id
    private String id;
    private String name;
    private int age;
    private int numberOfMatches;
    private int totalRuns;
    private int centuries;
    private int highestScore;
    private int totalWickets;
    private int mostWickets;
    private double avgWickets;
    private PlayerType playerType;
    private int battingSkill;
    private int bowlingSkill;
}
