package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Класс BookRepository должен")
class BookRepositoryTest {

    private static final String BOOK_NAME = "War and peace";
    private static final int EXPECTED_COUNT_OF_BOOKS = 1;
    private static final Author AUTHOR = new Author("Lev", "Tolstoy");
    private static final Genre GENRE = new Genre("Historical novel");
    private static final String BOOK_ID = "5";

    @Autowired
    private BookRepository repository;

    @Test
    @DisplayName("возвращать список всех книг")
    void shouldFindAllOfBooks() {

        var book = new Book(BOOK_ID, BOOK_NAME, AUTHOR, GENRE);
        repository.save(book).subscribe();

        var books = repository.findAll();
        StepVerifier
                .create(books)
                .expectNextCount(EXPECTED_COUNT_OF_BOOKS)
                .expectComplete()
                .verify();

    }

    @DisplayName("добавлять новую книгу")
    @Test
    void shouldInsertNewBook() {

        var book = new Book(BOOK_ID, BOOK_NAME, AUTHOR, GENRE);
        var bookMono = repository.save(book);

        StepVerifier
                .create(bookMono)
                .assertNext(result -> assertThat(result.getId()).isNotEmpty())
                .expectComplete()
                .verify();

        repository.deleteAll().subscribe();

    }

}
