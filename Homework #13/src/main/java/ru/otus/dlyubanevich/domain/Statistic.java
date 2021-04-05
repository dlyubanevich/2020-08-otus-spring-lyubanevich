package ru.otus.dlyubanevich.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Statistics")
public class Statistic {

    @Id
    private String id;
    private String season;
    private String league;
    private String team;
    private int position;
    private int games;
    private int points;

}
