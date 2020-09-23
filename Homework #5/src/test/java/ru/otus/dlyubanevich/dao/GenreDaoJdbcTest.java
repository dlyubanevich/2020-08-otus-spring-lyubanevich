package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(GenreDaoJdbc.class)
@DisplayName("Класс GenreDaoJdbc должен")
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("добавлять новый жанр")
    @Test
    void shouldAddGenre() {
        String name = "Poetry";
        Genre genre = new Genre(name);
        genre = genreDaoJdbc.save(genre);
        assertThat(genreDaoJdbc.getByName(name))
                .hasSize(1)
                .contains(genre);
    }

    @DisplayName("искать жанр по названию")
    @Test
    void shouldGetByName() {
        String name = "Novel";
        List<Genre> genres = genreDaoJdbc.getByName(name);
        assertThat(genres)
                .filteredOn(genre -> genre.getName().equals(name))
                .hasSize(1);
    }

    @Test
    @DisplayName("возвращать список все жанров")
    void shouldGetListOfGenres() {
        assertThat(genreDaoJdbc.getAll())
            .hasSize(3);
    }
}