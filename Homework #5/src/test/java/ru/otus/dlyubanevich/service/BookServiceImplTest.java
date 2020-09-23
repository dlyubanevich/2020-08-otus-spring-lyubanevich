package ru.otus.dlyubanevich.service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.dlyubanevich.dao.BookDao;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.service.exeption.BookAlreadyExistException;
import ru.otus.dlyubanevich.service.exeption.BookNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс BookServiceImpl должен")
@SpringBootTest
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

    @BeforeEach
    public void setUp(){
        Mockito.reset(bookDao);
    }

    @Test
    @DisplayName("добавлять книгу, если она отсутствует в библиотеке")
    void shouldAddTheBookIfAbsent() {

        given(bookDao.isExist(book)).willReturn(false);

        bookService.addBook(BOOK_NAME, author, genre);

        verify(bookDao, times(1)).isExist(book);
        verify(bookDao, times(1)).save(book);

    }

    @Test
    @DisplayName("бросать исключение, если книга присутствует в библиотеке")
    void shouldThrowExceptionIfBookIsAbsent() {

        given(bookDao.isExist(book)).willReturn(true);

        Throwable throwable = catchThrowable(() -> bookService.addBook(BOOK_NAME, author, genre));
        assertThat(throwable).isInstanceOf(BookAlreadyExistException.class);

    }

    @Test
    @DisplayName("удалять книгу по id")
    void shouldDeleteBookById() {

        given(bookDao.findById(ID)).willReturn(book);

        bookService.deleteBook(ID);

        verify(bookDao, times(1)).delete(book);

    }

    @Test
    @DisplayName("обновлять книгу, если она есть в библиотеке")
    void shouldUpdateBookIfAbsent() {

        given(bookDao.isExist(ID)).willReturn(true);

        bookService.updateBook(ID, BOOK_NAME, author, genre);

        verify(bookDao, times(1)).update(book);

    }

    @Test
    @DisplayName("бросать исключение при обновлении книги, которой нет в библиотеке")
    void shouldThrowExceptionWhenTryingUpdateAbsentBook() {

        given(bookDao.findById(ID)).willThrow(EmptyResultDataAccessException.class);

        Throwable throwable = catchThrowable(() -> bookService.updateBook(ID, BOOK_NAME, author, genre));
        assertThat(throwable).isInstanceOf(BookNotFoundException.class);

    }

}