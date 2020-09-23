package ru.otus.dlyubanevich.service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.dlyubanevich.dao.BookDao;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.service.exeption.BookAlreadyExistException;
import ru.otus.dlyubanevich.service.exeption.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс BookServiceImpl должен")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceImplTest {

    @MockBean
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    private final static String BOOK_NAME = "The Book";
    private final static String FIRST_NAME = "John";
    private final static String LAST_NAME = "Smith";
    private final static String GENRE = "genre";
    private final static long ID = 0;

    private final Author author = new Author(FIRST_NAME, LAST_NAME);
    private final Genre genre = new Genre(GENRE);
    private final Book book = new Book(BOOK_NAME, author, genre);

    @BeforeAll
    public void setUp(){
        Mockito.reset(bookDao);
    }

    @Test
    @DisplayName("добавлять книгу, если она отсутствует в библиотеке")
    void shouldAddTheBookIfAbsent() {

        List<Book> emptyList = new ArrayList<>();
        given(bookDao.find(book)).willReturn(emptyList);

        bookService.addBook(BOOK_NAME, author, genre);

        verify(bookDao, times(1)).find(book);
        verify(bookDao, times(1)).save(book);

    }

    @Test
    @DisplayName("бросать исключение, если книга присутствует в библиотеке")
    void shouldThrowExceptionIfBookIsAbsent() {

        List<Book> books = new ArrayList<>();
        books.add(book);
        given(bookDao.find(book)).willReturn(books);

        Throwable throwable = catchThrowable(() -> bookService.addBook(BOOK_NAME, author, genre));
        assertThat(throwable).isInstanceOf(BookAlreadyExistException.class);

    }

    @Test
    @DisplayName("удалять книгу по id")
    void shouldDeleteBookById() {

        List<Book> books = new ArrayList<>();
        books.add(book);
        given(bookDao.findById(ID)).willReturn(books);

        bookService.deleteBook(ID);

        verify(bookDao, times(1)).delete(book);

    }

    @Test
    @DisplayName("обновлять книгу, если она есть в библиотеке")
    void shouldUpdateBookIfAbsent() {

        List<Book> books = new ArrayList<>();
        books.add(book);
        given(bookDao.findById(ID)).willReturn(books);

        bookService.updateBook(ID, BOOK_NAME, author, genre);

        verify(bookDao, times(1)).update(book);

    }

    @Test
    @DisplayName("бросать исключение при обновлении книги, которой нет в библиотеке")
    void shouldThrowExceptionWhenTryingUpdateAbsentBook() {

        List<Book> emptyList = new ArrayList<>();
        given(bookDao.findById(ID)).willReturn(emptyList);

        Throwable throwable = catchThrowable(() -> bookService.updateBook(ID, BOOK_NAME, author, genre));
        assertThat(throwable).isInstanceOf(BookNotFoundException.class);

    }

}