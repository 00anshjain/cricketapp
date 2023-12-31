package com.demoproject.cricketapp.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("matches")
public class Match {
    @Id
    private String id;
    private LocalDateTime dateTime;

    private Scoreboard scoreboard;
    private int overs = 0;
    private Toss toss;

    private String matchWonByTeamID;
}
