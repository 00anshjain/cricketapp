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
public class PlayerScore {
    private String id;
    private String name;
    private int totalRuns;
    private int totalWickets;
    private PlayerType playerType;
    private int battingSkill;
    private int bowlingSkill;
}
