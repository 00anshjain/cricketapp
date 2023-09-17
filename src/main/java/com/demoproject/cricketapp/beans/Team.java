package com.demoproject.cricketapp.beans;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@Builder
@Document("teams")
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private String id;
    private String teamName;

    @DBRef
    private List<Player> players; // Use @DBRef to store references to Player documents

    private String captainId;
}
