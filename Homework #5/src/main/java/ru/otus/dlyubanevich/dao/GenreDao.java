package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();
    Genre save(Genre genre);
    List<Genre> getByName(String name);

}
