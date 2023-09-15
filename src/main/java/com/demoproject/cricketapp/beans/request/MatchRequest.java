package com.demoproject.cricketapp.beans.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchRequest {

    private LocalDateTime dateTime;
    private String team1Id;
    private String team2Id;
    private int overs;
}
