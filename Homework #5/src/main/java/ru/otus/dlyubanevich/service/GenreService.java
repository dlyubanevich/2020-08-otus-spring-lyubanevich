package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getGenresByName(String name);
    Genre saveGenre(Genre genre);

}
