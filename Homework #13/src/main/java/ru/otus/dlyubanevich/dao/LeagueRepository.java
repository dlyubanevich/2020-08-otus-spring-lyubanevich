package ru.otus.dlyubanevich.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dlyubanevich.domain.League;

public interface LeagueRepository extends JpaRepository<League, Long> {

    League findByName(String name);

}
