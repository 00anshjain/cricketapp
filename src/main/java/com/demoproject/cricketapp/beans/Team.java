package com.demoproject.cricketapp.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("teams")
@NoArgsConstructor
public class Team {
    @Id
    private String id;
    private String teamName;

    @DBRef
    private List<Player> players; // Use @DBRef to store references to Player documents

    private String captain;
}
