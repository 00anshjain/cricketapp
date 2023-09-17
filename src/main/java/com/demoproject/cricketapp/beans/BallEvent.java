package com.demoproject.cricketapp.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
public class BallEvent {
    private String id;
    private String matchID;
    private int ballNumber;

    @DBRef
    private String batsmanId; // Use @DBRef to store a reference to the Player document
    @DBRef
    private String bowlerId; // Use @DBRef to store a reference to the Player document

    private String result;
}
