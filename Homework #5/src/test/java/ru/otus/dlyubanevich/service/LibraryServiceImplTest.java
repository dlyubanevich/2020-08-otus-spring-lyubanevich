package ru.otus.dlyubanevich.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс LibraryServiceImpl должен")
@SpringBootTest
class LibraryServiceImplTest {

    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private BookService bookService;

    @Autowired
    private LibraryService libraryService;

    private final static String BOOK_NAME = "The Book";
    private final static String FIRST_NAME = "John";
    private final static String LAST_NAME = "Smith";
    private final static String GENRE = "genre";
    private final static long ID = 0;

    private final Author author = new Author(FIRST_NAME, LAST_NAME);
    private final Genre genre = new Genre(GENRE);

    @Test
    @DisplayName("получать из базы автора, жанр и создавать книгу")
    void shouldSaveTheBook() {

        List<Genre> genres = new ArrayList<>();
        genres.add(genre);
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        given(authorService.getAuthorsByName(FIRST_NAME, LAST_NAME)).willReturn(authors);
        given(genreService.getGenresByName(GENRE)).willReturn(genres);

        libraryService.addBook(BOOK_NAME, FIRST_NAME, LAST_NAME, GENRE);

        verify(authorService, times(1)).getAuthorsByName(FIRST_NAME, LAST_NAME);
        verify(genreService, times(1)).getGenresByName(GENRE);
        verify(bookService, times(1)).addBook(BOOK_NAME, author, genre);

    }

    @Test
    @DisplayName("получать все книги из базы")
    void shouldThrowExceptionWhenBookIsExist() {

        List<Book> books = new ArrayList<>();
        given(bookService.getAllBooks()).willReturn(books);

        libraryService.getAllBooks();

        verify(bookService, times(1)).getAllBooks();

    }

    @Test
    @DisplayName("обновлять книгу по id, автору и жанру")
    void shouldUpdateTheBook() {

        List<Genre> genres = new ArrayList<>();
        genres.add(genre);
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        given(authorService.getAuthorsByName(FIRST_NAME, LAST_NAME)).willReturn(authors);
        given(genreService.getGenresByName(GENRE)).willReturn(genres);

        libraryService.updateBook(ID, BOOK_NAME, FIRST_NAME, LAST_NAME, GENRE);

        verify(authorService, times(1)).getAuthorsByName(FIRST_NAME, LAST_NAME);
        verify(genreService, times(1)).getGenresByName(GENRE);
        verify(bookService, times(1)).updateBook(ID, BOOK_NAME, author, genre);
    }

    @Test
    @DisplayName("удалять книгу по id")
    void shouldDeleteTheBook() {

        libraryService.deleteBook(ID);
        verify(bookService, times(1)).deleteBook(ID);

    }

    @Test
    @DisplayName("находить книгу по имени")
    void shouldFindBookByName() {

        var emptyAuthor = new Author("", "");
        var emptyGenre = new Genre("");

        List<Book> books = new ArrayList<>();
        books.add(new Book(BOOK_NAME, author, genre));

        given(bookService.findBooksByOneOfAttributes(BOOK_NAME, emptyAuthor, emptyGenre)).willReturn(books);

        libraryService.findBooksByName(BOOK_NAME);

        verify(bookService, times(1)).findBooksByOneOfAttributes(BOOK_NAME, emptyAuthor, emptyGenre);

    }
}