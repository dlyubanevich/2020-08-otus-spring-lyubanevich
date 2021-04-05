package ru.otus.dlyubanevich.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "summaries")
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = Team.class, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(targetEntity = Season.class, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne(targetEntity = League.class, fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "league_id", nullable = false)
    private League league;

    @Column(name = "position")
    private int position;

    @Column(name = "games")
    private int games;

    @Column(name = "points")
    private int points;

    public Summary(Team team, Season season, League league, int position, int games, int points) {
        this.id = 0;
        this.team = team;
        this.season = season;
        this.league = league;
        this.position = position;
        this.games = games;
        this.points = points;
    }

}
