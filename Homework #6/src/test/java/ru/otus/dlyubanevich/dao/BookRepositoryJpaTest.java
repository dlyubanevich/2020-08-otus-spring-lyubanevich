package ru.otus.dlyubanevich.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookRepositoryJpa.class)
@DisplayName("Класс BookDaoJdbc должен")
class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa repository;

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

        var books = repository.getAll();

        assertThat(books)
                .hasSize(EXPECTED_COUNT_OF_ALL_BOOKS)
                .allMatch(b-> b.getGenres().size() == 1 && b.getAuthors().size() == 1 && b.getComments().size() >=0);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);

    }

    @DisplayName("добавлять новую книгу")
    @Test
    void shouldInsertNewBook() {

        var author = entityManager.find(Author.class, FIRST_AUTHOR_ID);
        var genre = entityManager.find(Genre.class, THIRD_GENRE_ID);
        var book = new Book(0, NEW_BOOK_NAME, Collections.singletonList(author), Collections.singletonList(genre), null);

        book = repository.save(book);

        var bookAfterSave = entityManager.find(Book.class, book.getId());
        assertThat(bookAfterSave)
                .isNotNull()
                .isEqualToComparingFieldByField(book);

    }

    @DisplayName("удалять книгу")
    @Test
    void shouldDeleteTheBook() {

        repository.delete(BOOK_ID);

        var foundedBook = entityManager.find(Book.class, BOOK_ID);
        assertThat(foundedBook)
                .isNull();

    }

    @DisplayName("обновлять сведения об авторе, жанре или названии")
    @Test
    void shouldUpdateTheBook() {

        var book = entityManager.find(Book.class, BOOK_ID_FOR_UPDATE);

        List<Author> authors = book.getAuthors();
        authors.clear();
        authors.add(entityManager.find(Author.class, FIRST_AUTHOR_ID));

        List<Genre> genres = book.getGenres();
        genres.clear();
        genres.add(entityManager.find(Genre.class, THIRD_GENRE_ID));

        book.setName(NEW_BOOK_NAME);
        repository.save(book);

        var booksAfterUpdate = repository.getAll();
        assertThat(booksAfterUpdate).contains(book);

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
        var notMatchBook = new Book(0, matchBook.getName(), Collections.singletonList(author), Collections.singletonList(genre), null);

        assertThat(repository.isExist(notMatchBook))
                .isEqualTo(false);
    }

}