package com.demoproject.cricketapp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
public class BallEvent {
    private String id;
    private String matchID;
    private int ballNumber;

    @DBRef
    private Player batsman; // Use @DBRef to store a reference to the Player document
    @DBRef
    private Player bowler; // Use @DBRef to store a reference to the Player document

    private String result;
}
