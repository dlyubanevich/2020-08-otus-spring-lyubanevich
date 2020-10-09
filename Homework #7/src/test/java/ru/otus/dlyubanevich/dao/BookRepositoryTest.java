package ru.otus.dlyubanevich.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс BookRepository должен")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private static final String NEW_BOOK_NAME = "new Book name";
    private final static int EXPECTED_COUNT_OF_ALL_BOOKS = 6;
    private final static long FIRST_AUTHOR_ID = 1;
    private final static long THIRD_GENRE_ID = 3;
    private final static long BOOK_ID = 1;
    private static final long BOOK_ID_FOR_UPDATE = 3;
    private static final int EXPECTED_QUERIES_COUNT = 3;

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldGetListOfBooks() {

        SessionFactory sessionFactory = entityManager.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        var books = repository.findAll();

        assertThat(books)
                .hasSize(EXPECTED_COUNT_OF_ALL_BOOKS)
                .allMatch(b-> b.getGenres().size() == 1 && b.getAuthors().size() == 1);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);

    }

    @DisplayName("добавлять новую книгу")
    @Test
    void shouldInsertNewBook() {

        var author = entityManager.find(Author.class, FIRST_AUTHOR_ID);
        var genre = entityManager.find(Genre.class, THIRD_GENRE_ID);
        var book = new Book(NEW_BOOK_NAME, Collections.singleton(author), Collections.singleton(genre));

        book = repository.save(book);

        var bookAfterSave = entityManager.find(Book.class, book.getId());
        assertThat(bookAfterSave)
                .isNotNull()
                .isEqualToComparingFieldByField(book);

    }

    @DisplayName("удалять книгу")
    @Test
    void shouldDeleteTheBook() {

        repository.deleteById(BOOK_ID);

        var foundedBook = entityManager.find(Book.class, BOOK_ID);
        assertThat(foundedBook)
                .isNull();

    }

    @DisplayName("обновлять сведения об авторе, жанре или названии")
    @Test
    void shouldUpdateTheBook() {

        var book = entityManager.find(Book.class, BOOK_ID_FOR_UPDATE);

        Set<Author> authors = book.getAuthors();
        authors.clear();
        authors.add(entityManager.find(Author.class, FIRST_AUTHOR_ID));

        Set<Genre> genres = book.getGenres();
        genres.clear();
        genres.add(entityManager.find(Genre.class, THIRD_GENRE_ID));

        book.setName(NEW_BOOK_NAME);
        repository.save(book);

        var bookAfterUpdate = repository.findById(BOOK_ID_FOR_UPDATE).orElseThrow();
        assertThat(bookAfterUpdate)
                .isEqualToComparingFieldByField(book);

    }

    @Test
    @DisplayName("находить книгу по id")
    void shouldFindBookById() {
        var optionalFoundedBook = repository.findById(BOOK_ID);
        var foundedBook = entityManager.find(Book.class, BOOK_ID);
        assertThat(optionalFoundedBook).isPresent().get()
                .isEqualToComparingFieldByField(foundedBook);
    }

    @Test
    @DisplayName("определять наличие книги в БД по имени, авторам и жанрам")
    void shouldCheckBookForExist() {

        var matchBook = entityManager.find(Book.class, BOOK_ID_FOR_UPDATE);
        assertThat(repository.isExist(matchBook))
                .isEqualTo(true);

        var author = entityManager.find(Author.class, FIRST_AUTHOR_ID);
        var genre = entityManager.find(Genre.class, THIRD_GENRE_ID);
        var notMatchBook = new Book(matchBook.getName(), Collections.singleton(author), Collections.singleton(genre));

        assertThat(repository.isExist(notMatchBook))
                .isEqualTo(false);
    }

}