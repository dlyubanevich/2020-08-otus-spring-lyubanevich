package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Класс BookRepository должен")
class BookRepositoryTest {

    private static final int TOTAL_COUNT_OF_BOOKS = 6;
    private static final String BOOK_NAME = "War and peace";
    private static final int EXPECTED_COUNT_OF_BOOKS = 1;
    private static final Author AUTHOR = new Author("Lev", "Tolstoy");
    private static final Genre GENRE = new Genre("Historical novel");
    private static final String NEW_BOOK_NAME = "new Book name";
    private static final String BOOK_ID = "5";

    @Autowired
    private BookRepository repository;

    @Test
    @DisplayName("возвращать список всех книг")
    void shouldFindAllOfBooks() {

        List<Book> books = repository.findAll();
        assertThat(books)
                .hasSize(TOTAL_COUNT_OF_BOOKS);

    }

    @Test
    @DisplayName("находить книги по имени")
    void shouldFindBookByName() {

        List<Book> books = repository.findByName(BOOK_NAME);
        assertThat(books)
                .hasSize(EXPECTED_COUNT_OF_BOOKS)
                .allMatch(book -> book.getName().equals(BOOK_NAME));

    }

    @Test
    @DisplayName("находить книги по автору")
    void shouldFindBooksByAuthor() {

        List<Book> books = repository.findByAuthorsIsContaining(AUTHOR);
        assertThat(books)
                .hasSize(EXPECTED_COUNT_OF_BOOKS)
                .allMatch(book -> book.getName().equals(BOOK_NAME)
                        && book.getAuthors().contains(AUTHOR));

    }

    @Test
    @DisplayName("находить книги по жанру")
    void shouldFindBooksByGenre() {

        List<Book> books = repository.findByGenresContains(GENRE);
        assertThat(books)
                .hasSize(EXPECTED_COUNT_OF_BOOKS)
                .allMatch(book -> book.getName().equals(BOOK_NAME)
                        && book.getAuthors().contains(AUTHOR)
                        && book.getGenres().contains(GENRE));

    }

    @Test
    @DisplayName("находить книгу по id")
    void shouldFindBookById() {

        var optionalFoundedBook = repository.findById(BOOK_ID);
        var book = new Book(BOOK_ID, BOOK_NAME, Collections.singleton(AUTHOR), Collections.singletonList(GENRE));
        assertThat(optionalFoundedBook).isPresent().get()
                .isEqualToComparingFieldByField(book);

    }

    @DisplayName("добавлять новую книгу")
    @Test
    void shouldInsertNewBook() {

        var book = new Book(NEW_BOOK_NAME, AUTHOR, GENRE);
        book = repository.save(book);

        var bookAfterSave = repository.findById(book.getId());
        assertThat(bookAfterSave)
                .isNotEmpty()
                .get()
                .isEqualToComparingFieldByField(book);

    }

    @DisplayName("удалять книгу")
    @Test
    void shouldDeleteTheBook() {

        repository.deleteById(BOOK_ID);

        var foundedBook = repository.findById(BOOK_ID);

        assertThat(foundedBook).isEmpty();

    }

    @Test
    @DisplayName("определять наличие книги в БД по имени, авторам и жанрам")
    void shouldCheckBookForExist() {

        var matchBook = new Book(BOOK_ID, BOOK_NAME, Collections.singleton(AUTHOR), Collections.singletonList(GENRE));
        assertThat(repository.isExist(matchBook))
                .isEqualTo(true);

        var notMatchBook = new Book(BOOK_ID, BOOK_NAME, Collections.singleton(AUTHOR), new ArrayList<>());
        assertThat(repository.isExist(notMatchBook))
                .isEqualTo(false);
    }

    @DisplayName("обновлять сведения об авторе, жанре или названии")
    @Test
    void shouldUpdateTheBook() {

        var book = repository.findById(BOOK_ID).orElseThrow();
        var authors = book.getAuthors();
        authors.clear();

        var genres = book.getGenres();
        genres.clear();

        book.setName(NEW_BOOK_NAME);
        repository.save(book);

        var bookAfterUpdate = repository.findById(BOOK_ID).orElseThrow();
        assertThat(bookAfterUpdate)
                .isEqualToComparingFieldByField(book);

    }

}