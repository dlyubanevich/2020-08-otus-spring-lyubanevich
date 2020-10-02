package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
@DisplayName("Класс GenreRepositoryJpa должен")
class GenreRepositoryJpaTest {

    private static final String NEW_GENRE_NAME = "Poetry";
    private static final String SECOND_GENRE_NAME = "Novel";
    private static final int EXPECTED_COUNT_OF_ALL_GENRES = 3;
    private static final int EXPECTED_GENRES_COUNT = 1;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRepositoryJpa repository;

    @Test
    @DisplayName("возвращать список все жанров")
    void shouldGetListOfGenres() {
        assertThat(repository.getAll())
                .hasSize(EXPECTED_COUNT_OF_ALL_GENRES);
    }

    @DisplayName("добавлять новый жанр")
    @Test
    void shouldAddGenre() {
        var genre = new Genre(0, NEW_GENRE_NAME);
        genre = repository.save(genre);
        var newGenre = entityManager.find(Genre.class, genre.getId());

        assertThat(newGenre)
                .isNotNull()
                .matches(g -> g.getName().equals(NEW_GENRE_NAME));
    }

    @DisplayName("искать жанр по названию")
    @Test
    void shouldGetByName() {
        List<Genre> genres = repository.findByName(SECOND_GENRE_NAME);
        assertThat(genres)
                .allMatch(genre -> genre.getName().equals(SECOND_GENRE_NAME))
                .hasSize(EXPECTED_GENRES_COUNT);
    }

}