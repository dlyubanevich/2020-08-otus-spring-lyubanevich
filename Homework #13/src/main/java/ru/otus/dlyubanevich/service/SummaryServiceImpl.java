package ru.otus.dlyubanevich.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.dao.LeagueRepository;
import ru.otus.dlyubanevich.dao.SeasonRepository;
import ru.otus.dlyubanevich.dao.SummaryRepository;
import ru.otus.dlyubanevich.dao.TeamRepository;
import ru.otus.dlyubanevich.domain.*;
import ru.otus.dlyubanevich.dto.SummaryDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;
    private final SummaryRepository summaryRepository;

    @Override
    public Summary transform(Statistic statistic) {
        return new Summary(
                new Team(statistic.getTeam()),
                new Season(statistic.getSeason()),
                new League(statistic.getLeague()),
                statistic.getPosition(),
                statistic.getGames(),
                statistic.getPoints()
        );
    }

    @Transactional
    @Override
    public void save(Summary summary) {
        summary.setTeam(saveTeamIfNotExist(summary.getTeam()));
        summary.setSeason(saveSeasonIfNotExist(summary.getSeason()));
        summary.setLeague(saveLeagueIfNotExist(summary.getLeague()));
        summaryRepository.save(summary);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SummaryDto> getAllSummary() {
        return summaryRepository.findAll().stream().map(SummaryDto::new).collect(Collectors.toList());
    }

    public League saveLeagueIfNotExist(League league) {
        var savedLeague = leagueRepository.findByName(league.getName());
        if (savedLeague == null){
            savedLeague = leagueRepository.save(league);
        }
        return savedLeague;
    }

    public Team saveTeamIfNotExist(Team team) {
        var savedTeam = teamRepository.findByName(team.getName());
        if (savedTeam == null){
            savedTeam = teamRepository.save(team);
        }
        return savedTeam;
    }

    public Season saveSeasonIfNotExist(Season season) {
        var savedSeason = seasonRepository.findByName(season.getName());
        if (savedSeason == null){
            savedSeason = seasonRepository.save(season);
        }
        return savedSeason;
    }

}
