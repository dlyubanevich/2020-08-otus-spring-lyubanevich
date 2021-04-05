package ru.otus.dlyubanevich.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dlyubanevich.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByName(String name);

}
