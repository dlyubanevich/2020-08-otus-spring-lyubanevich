package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findByName(String name);
    Genre save(Genre genre);
    Genre findById(long id);

}
