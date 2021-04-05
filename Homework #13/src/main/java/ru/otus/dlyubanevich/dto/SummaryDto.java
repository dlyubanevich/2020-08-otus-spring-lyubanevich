package ru.otus.dlyubanevich.dto;

import ru.otus.dlyubanevich.domain.League;
import ru.otus.dlyubanevich.domain.Season;
import ru.otus.dlyubanevich.domain.Summary;
import ru.otus.dlyubanevich.domain.Team;

public class SummaryDto {

    private final String description;

    public SummaryDto(Summary summary){
        this.description = createDescription(summary);
    }

    private String createDescription(Summary summary){
        return getHeader(summary)
                + getTeam(summary.getTeam())
                + getLeague(summary.getLeague())
                + getSeason(summary.getSeason())
                + getResults(summary);
    }

    private String getHeader(Summary summary) {
        return "Summary (id=" + summary.getId() + ")";
    }

    private String getTeam(Team team) {
        return "Team (id=" + team.getId() + ", name=" + team.getName() + ")";
    }

    private String getLeague(League league) {
        return "League (id=" + league.getId() + ", name=" + league.getName() + ")";
    }

    private String getSeason(Season season) {
        return "Season (id=" + season.getId() + ", name=" + season.getName() + ")";
    }

    private String getResults(Summary summary) {
        return "position:" + summary.getPosition() + ", games: " + summary.getGames() + ", points:" + summary.getPoints();
    }

    @Override
    public String toString() {
        return this.description;
    }
}
