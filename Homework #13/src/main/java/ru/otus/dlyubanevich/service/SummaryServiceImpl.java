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

    @Transactional(readOnly = true)
    @Override
    public List<SummaryDto> getAllSummary() {
        return summaryRepository.findAll().stream().map(SummaryDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public League findByName(League league) {
        return leagueRepository.findByName(league.getName());
    }

    @Transactional(readOnly = true)
    @Override
    public Season findByName(Season season) {
        return seasonRepository.findByName(season.getName());
    }

    @Transactional(readOnly = true)
    @Override
    public Team findByName(Team team) {
        return teamRepository.findByName(team.getName());
    }

}
