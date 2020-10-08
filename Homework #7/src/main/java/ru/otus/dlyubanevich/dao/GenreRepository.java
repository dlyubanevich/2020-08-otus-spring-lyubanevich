package ru.otus.dlyubanevich.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByName(String name);

}
