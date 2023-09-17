package com.demoproject.cricketapp.beans;

import com.demoproject.cricketapp.commons.enums.TossChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

//    @DBRef Removed as if team captain changed, player changed should not affect match object
//    private Team team1; // Use @DBRef to store a reference to the Team document
////    @DBRef
//    private Team team2;

    private Scoreboard scoreboard;
    private int overs;
    private Toss toss;

    private String matchWonByTeamID;
}
