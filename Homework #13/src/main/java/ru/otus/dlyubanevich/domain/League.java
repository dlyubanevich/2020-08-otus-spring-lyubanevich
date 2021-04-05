package ru.otus.dlyubanevich.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "leagues")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY)
    private List<Summary> summaries;

    public League(String name) {
        this.id = 0;
        this.name = name;
        this.summaries = Collections.emptyList();
    }

}
