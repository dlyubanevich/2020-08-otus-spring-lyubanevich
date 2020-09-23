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

    @DisplayName("возвращать список из 3-х авторов")
    @Test
    void shouldGetListOfAuthors() {
        var authors = authorDaoJdbc.getAll();
        assertThat(authors)
                .hasSize(3);
    }

    @Test
    @DisplayName("искать авторов по имени и фамилии")
    void shouldGetListOfAuthorsByName() {
        var firstname = "Robert";
        var lastName = "Martin";
        var foundedAuthors = authorDaoJdbc.getByName(firstname, lastName);
        assertThat(foundedAuthors)
                .filteredOn(author -> author.getFirstName().equals(firstname) && author.getLastName().equals(lastName))
                .hasSize(1);
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
                .hasSize(1)
                .contains(newAuthor);
    }
}