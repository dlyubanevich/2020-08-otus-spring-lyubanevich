package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.dao.GenreRepository;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceOrm implements GenreService {

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre findById(long id) {
        return genreRepository.findById(id).orElseThrow();
    }
}
