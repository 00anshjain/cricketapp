package com.demoproject.cricketapp.beans.response;

import com.demoproject.cricketapp.beans.Scoreboard;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MatchInfoResponse {
    private String id;
    private LocalDateTime dateTime;
    private Scoreboard scoreboard;
    private String matchWonByTeamID;
}
