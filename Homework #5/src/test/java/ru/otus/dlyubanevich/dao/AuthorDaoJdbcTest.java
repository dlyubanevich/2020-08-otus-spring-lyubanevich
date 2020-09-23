package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dlyubanevich.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName("Класс AuthorDaoJdbc должен")
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    private final static int COUNT_OF_AUTHORS = 3;
    private final static int EXPECTED_COUNT_OF_AUTHORS = 1;

    @DisplayName("возвращать список из 3-х авторов")
    @Test
    void shouldGetListOfAuthors() {
        var authors = authorDaoJdbc.getAll();
        assertThat(authors)
                .hasSize(COUNT_OF_AUTHORS);
    }

    @Test
    @DisplayName("искать авторов по имени и фамилии")
    void shouldGetListOfAuthorsByName() {
        var firstname = "Robert";
        var lastName = "Martin";
        var foundedAuthors = authorDaoJdbc.getByName(firstname, lastName);
        assertThat(foundedAuthors)
                .filteredOn(author -> author.getFirstName().equals(firstname) && author.getLastName().equals(lastName))
                .hasSize(EXPECTED_COUNT_OF_AUTHORS);
    }

    @Test
    @DisplayName("добавлять нового автора и присваивать ему id")
    void shouldInsertNewAuthor() {
        var firstname = "Steve";
        var lastName = "McConnell";
        var authorsBeforeSave = authorDaoJdbc.getByName(firstname, lastName);
        var newAuthor = authorDaoJdbc.save(new Author(firstname, lastName));
        var authorsAfterSave = authorDaoJdbc.getByName(firstname, lastName);
        assertThat(authorsAfterSave)
                .hasSizeGreaterThan(authorsBeforeSave.size())
                .filteredOn(author -> author.getId() > 0)
                .hasSize(EXPECTED_COUNT_OF_AUTHORS)
                .contains(newAuthor);
    }
}