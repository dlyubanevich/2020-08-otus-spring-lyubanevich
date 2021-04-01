package ru.otus.dlyubanevich.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dlyubanevich.domain.Season;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    Season findByName(String name);

}
