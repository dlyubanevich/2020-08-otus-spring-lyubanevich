package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName("Класс BookDaoJdbc должен")
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("добавлять новую книгу")
    @Test
    void shouldInsertNewBook() {
        var booksBeforeSave = bookDaoJdbc.getAll();
        var author = new Author("Robert", "Martin");
        author.setId(1);
        var genre = new Genre("Computer science");
        genre.setId(3);
        var book = new Book("Book", author, genre);
        book = bookDaoJdbc.save(book);
        var booksAfterSave = bookDaoJdbc.getAll();
        assertThat(booksAfterSave)
                .contains(book)
                .hasSizeGreaterThan(booksBeforeSave.size());
    }

    @DisplayName("удалять книгу")
    @Test
    void shouldDeleteTheBook() {
        var booksBeforeDelete = bookDaoJdbc.getAll();
        var book = booksBeforeDelete.get(0);
        bookDaoJdbc.delete(book);
        var booksAfterDelete = bookDaoJdbc.getAll();
        assertThat(booksAfterDelete)
                .doesNotContain(book)
                .hasSizeLessThan(booksBeforeDelete.size());
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldUpdateTheBook() {
        var author = new Author("Robert", "Martin");
        author.setId(1);
        var genre = new Genre("Computer science");
        genre.setId(3);
        var book = new Book("new Book", author, genre);
        book.setId(1);
        bookDaoJdbc.update(book);
        var booksAfterUpdate = bookDaoJdbc.getAll();
        assertThat(booksAfterUpdate).contains(book);
    }

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldGetListOfBooks() {
        var books = bookDaoJdbc.getAll();
        assertThat(books)
                .hasSize(6);
    }

    @Test
    @DisplayName("находить книгу по id")
    void shouldFindBookById() {
        var books = bookDaoJdbc.getAll();
        var book = books.get(0);
        var foundedBooks = bookDaoJdbc.findById(book.getId());
        assertThat(foundedBooks)
                .hasSize(1)
                .contains(book);
    }

    @Test
    @DisplayName("находить книгу по имени")
    void shouldFindBookByName() {

        String name = "War and peace";
        var author = new Author("", "");
        var genre = new Genre("");
        var book = new Book(name, author, genre);

        var foundedBooks = bookDaoJdbc.find(book);

        assertThat(foundedBooks)
                .hasSize(1)
                .extracting(Book::getName)
                .contains(name);
    }

    @Test
    @DisplayName("находить книгу по автору")
    void shouldFindBookByAuthor() {

        String name = "";
        var author = new Author("Lev", "Tolstoy");
        author.setId(2);
        var genre = new Genre("");
        var book = new Book(name, author, genre);

        var foundedBooks = bookDaoJdbc.find(book);

        assertThat(foundedBooks)
                .hasSize(1)
                .extracting(Book::getName)
                .contains("War and peace");
    }

    @Test
    @DisplayName("находить книгу по жанру")
    void shouldFindBookByGenre() {

        String name = "";
        var author = new Author("", "");
        var genre = new Genre("Historical novel");
        genre.setId(1);
        var book = new Book(name, author, genre);

        var foundedBooks = bookDaoJdbc.find(book);

        assertThat(foundedBooks)
                .hasSize(1)
                .extracting(Book::getName)
                .contains("War and peace");
    }

}