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
    public Genre getGenreByName(String name) {
        Genre genre;
        List<Genre> genres = genreDao.getByName(name);
        if (genres.size() != 0){
            genre = genres.get(0);
        }else{
            genre = saveGenre(name);
        }
        return genre;
    }

    private Genre saveGenre(String name) {
        Genre genre = new Genre(name);
        return genreDao.save(genre);
    }

}
