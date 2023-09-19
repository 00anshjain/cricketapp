package com.demoproject.cricketapp.beans.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MatchInfoResponse {
    private String id;
    private LocalDateTime dateTime;
    private String team1Id;
    private String team2Id;
    private String matchWonByTeamID;
}

