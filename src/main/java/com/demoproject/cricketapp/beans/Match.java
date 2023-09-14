package com.demoproject.cricketapp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document("matches")
public class Match {
    @Id
    private String id;
    private LocalDateTime dateTime;

    @DBRef
    private Team team1; // Use @DBRef to store a reference to the Team document
    @DBRef
    private Team team2; // Use @DBRef to store a reference to the Team document

    @DBRef
    private Scoreboard scoreboard;
    private int overs;
    private String toss;

    private String tossWonByTeamId;
    private String matchWonByTeamID;
}
