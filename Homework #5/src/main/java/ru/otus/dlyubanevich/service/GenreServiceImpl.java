package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.dao.GenreDao;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> getGenresByName(String name) {
        return genreDao.getByName(name);
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreDao.save(genre);
    }
}
