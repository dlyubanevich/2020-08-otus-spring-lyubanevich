package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<Genre> getAll();
    Genre save(Genre genre);
    List<Genre> findByName(String name);
    Optional<Genre> findById(long id);

}
