package ru.otus.dlyubanevich.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.dlyubanevich.dao.BookRepository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.service.exeption.BookAlreadyExistException;
import ru.otus.dlyubanevich.service.exeption.BookNotFoundException;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс BookServiceImpl должен")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class BookServiceImplTest {

    private static final String NAME_OF_NEW_BOOK = "new Book";
    private static final long EMPTY_ID = 0;
    private static final Author AUTHOR = new Author("First name", "Last name");
    private static final Genre GENRE = new Genre("Genre");

    @MockBean
    private BookRepository repository;

    @Autowired
    private BookService bookService;

    @BeforeEach
    public void setUp(){
        Mockito.reset(repository);
    }

    @Test
    @DisplayName("сохранять книгу, если книга отсутствует")
    void shouldSaveTheBookIfBookDoesNotExist() {

        var book = getNewBook();

        given(repository.isExist(book)).willReturn(false);
        given(repository.save(book)).willReturn(book);

        var savedBook = bookService.save(book);

        verify(repository, times(1)).isExist(book);
        verify(repository, times(1)).save(book);

        assertThat(savedBook)
                .isNotNull()
                .isEqualTo(book);

    }

    @Test
    @DisplayName("при сохранении книги бросать исключение, если книга уже есть в базе")
    void shouldThrowExceptionIfBookIsAlreadyExist() {

        var book = getNewBook();
        given(repository.isExist(book)).willReturn(true);
        given(repository.save(book)).willReturn(book);

        var thrown = catchThrowable(() -> bookService.save(book));

        assertThat(thrown)
                .isInstanceOf(BookAlreadyExistException.class)
                .hasMessageContaining("Book with specified parameters is already exist!");
        verify(repository, times(1)).isExist(book);
        verify(repository, times(0)).save(book);

    }

    @Test
    @DisplayName("возвращать список всех книг")
    void shouldReturnListOfAllBooks() {

        var books = Collections.singletonList(getNewBook());
        given(repository.findAll()).willReturn(books);

        var foundedBooks = bookService.getAll();

        verify(repository, times(1)).findAll();
        assertThat(foundedBooks)
                .isEqualTo(books);

    }

    @Test
    @DisplayName("вызывать удаление книги по id")
    void shouldDeleteBookById() {
        bookService.delete(EMPTY_ID);
        verify(repository, times(1)).deleteById(EMPTY_ID);
    }

    @Test
    @DisplayName("находить список книг по имени")
    void shouldFindBooksByName() {

        var books = Collections.singletonList(getNewBook());
        given(repository.findByName(NAME_OF_NEW_BOOK)).willReturn(books);

        var foundedBooks = bookService.findByName(NAME_OF_NEW_BOOK);

        verify(repository, times(1)).findByName(NAME_OF_NEW_BOOK);
        assertThat(foundedBooks)
                .isEqualTo(books);

    }

    @Test
    @DisplayName("находить книгу по id")
    void shouldFindBookById() {

        var optionalBook = Optional.of(getNewBook());
        given(repository.findById(EMPTY_ID)).willReturn(optionalBook);

        var foundedBook = bookService.findById(EMPTY_ID);

        verify(repository, times(1)).findById(EMPTY_ID);
        assertThat(foundedBook)
                .isEqualTo(optionalBook.get());

    }

    @Test
    @DisplayName("бросать исключение, если по id книга не найдена")
    void shouldThrowExceptionIdBookIsNotFoundById() {

        Optional<Book> optionalBook = Optional.empty();
        given(repository.findById(EMPTY_ID)).willReturn(optionalBook);

        var thrown = catchThrowable(() -> bookService.findById(EMPTY_ID));

        assertThat(thrown)
                .isInstanceOf(BookNotFoundException.class);
        verify(repository, times(1)).findById(EMPTY_ID);

    }

    @Nested
    @DisplayName("добавлять сведения о книге")
    class AddInfo {

        @Test
        @DisplayName("при попытке добавления сведений о книге бросать исключение, если книга по id не найдена")
        void shouldThrowExceptionWhenAddBookInfoAndBookIsNotFoundById() {

            Optional<Book> optionalBook = Optional.empty();
            given(repository.findById(EMPTY_ID)).willReturn(optionalBook);

            var thrown = catchThrowable(() -> bookService.addAuthor(EMPTY_ID, AUTHOR));

            assertThat(thrown)
                    .isInstanceOf(BookNotFoundException.class);
            verify(repository, times(1)).findById(EMPTY_ID);

        }

        @Test
        @DisplayName("при добавлении сведений должна находиться книга по id и перезаписываться")
        void shouldFindBookByIdAndSaveTheBook() {

            var book = prepareEmptyBook();
            prepareMockRepositoryForAddingInfo(book);

            bookService.addAuthor(book.getId(), AUTHOR);

            verify(repository, times(1)).findById(EMPTY_ID);
            verify(repository, times(1)).save(book);

        }

        @Test
        @DisplayName("добавлять автора")
        void shouldAddAuthorToTheBook() {

            var book = prepareEmptyBook();
            prepareMockRepositoryForAddingInfo(book);

            bookService.addAuthor(book.getId(), AUTHOR);

            assertThat(book)
                    .matches(updatedBook -> updatedBook.getName().equals(NAME_OF_NEW_BOOK))
                    .matches(updatedBook -> updatedBook.getAuthors().size() == 1)
                    .matches(updatedBook -> updatedBook.getGenres().size() == 0);

            verify(repository, times(1)).findById(EMPTY_ID);
            verify(repository, times(1)).save(book);

        }

        @Test
        @DisplayName("добавлять жанр")
        void shouldAddGenreToTheBook() {

            var book = prepareEmptyBook();
            prepareMockRepositoryForAddingInfo(book);

            bookService.addGenre(book.getId(), GENRE);

            assertThat(book)
                    .matches(updatedBook -> updatedBook.getName().equals(NAME_OF_NEW_BOOK))
                    .matches(updatedBook -> updatedBook.getGenres().size() == 1)
                    .matches(updatedBook -> updatedBook.getAuthors().size() == 0);

        }

        @Test
        @DisplayName("менять название книги")
        void shouldChangeTheNameOfTheBook() {

            var book = prepareEmptyBook();
            book.setName("");
            prepareMockRepositoryForAddingInfo(book);

            bookService.changeName(book.getId(), NAME_OF_NEW_BOOK);

            assertThat(book)
                    .matches(updatedBook -> updatedBook.getName().equals(NAME_OF_NEW_BOOK))
                    .matches(updatedBook -> updatedBook.getAuthors().size() == 0 && updatedBook.getGenres().size() == 0);
        }

        private Book prepareEmptyBook(){
            var book = getNewBook();
            book.getAuthors().clear();
            book.getGenres().clear();
            return book;
        }

        private void prepareMockRepositoryForAddingInfo(Book book){
            Optional<Book> optionalBook = Optional.of(book);
            given(repository.findById(EMPTY_ID)).willReturn(optionalBook);
            given(repository.save(book)).willReturn(book);
        }

    }

    private Book getNewBook(){

        Set<Author> authors = new HashSet<>();
        authors.add(AUTHOR);
        Set<Genre> genres = new HashSet<>();
        genres.add(GENRE);

       return new Book(
               NAME_OF_NEW_BOOK,
               authors,
               genres
       );

    }
}