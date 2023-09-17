package com.demoproject.cricketapp.beans;

import com.demoproject.cricketapp.commons.enums.TossChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scoreboard {
    private String id;
    private Team team1; // Use @DBRef to store a reference to the Team document
    private Team team2; // Use @DBRef to store a reference to the Team document
}
