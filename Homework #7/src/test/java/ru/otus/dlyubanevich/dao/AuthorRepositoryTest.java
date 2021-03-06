package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.dlyubanevich.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс AuthorRepository должен")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private static final long FIRST_AUTHOR_ID = 1;
    private final static int COUNT_OF_AUTHORS = 3;
    private final static int EXPECTED_COUNT_OF_AUTHORS = 1;

    @DisplayName("возвращать список из всех авторов")
    @Test
    void shouldGetListOfAuthors() {
        var authors = repository.findAll();
        assertThat(authors)
                .hasSize(COUNT_OF_AUTHORS);
    }

    @Test
    @DisplayName("искать авторов по имени и фамилии")
    void shouldGetListOfAuthorsByName() {

        var firstname = "Robert";
        var lastName = "Martin";
        var foundedAuthors = repository.findByFirstNameAndLastName(firstname, lastName);
        var firstAuthor = entityManager.find(Author.class, FIRST_AUTHOR_ID);

        assertThat(foundedAuthors)
                .allMatch(author -> author.getFirstName().equals(firstname) && author.getLastName().equals(lastName))
                .hasSize(EXPECTED_COUNT_OF_AUTHORS)
                .contains(firstAuthor);
    }

    @Test
    @DisplayName("добавлять нового автора и присваивать ему id")
    void shouldSaveNewAuthorAndSetId() {
        var firstname = "Steve";
        var lastName = "McConnell";

        var authorsBeforeSave = repository.findByFirstNameAndLastName(firstname, lastName);
        assertThat(authorsBeforeSave).hasSize(0);

        var newAuthor = repository.save(new Author(0, firstname, lastName));
        assertThat(newAuthor.getId()).isGreaterThan(0);

        var authorsAfterSave = entityManager.find(Author.class, newAuthor.getId());
        assertThat(authorsAfterSave)
                .matches(author -> author.getFirstName().equals(firstname) && author.getLastName().equals(lastName));
    }
}