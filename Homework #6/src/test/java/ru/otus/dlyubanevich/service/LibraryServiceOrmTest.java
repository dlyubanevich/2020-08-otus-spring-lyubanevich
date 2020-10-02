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
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Comment;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.dto.BookView;
import ru.otus.dlyubanevich.service.exeption.CommentNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Класс LibraryServiceImpl должен")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class LibraryServiceOrmTest {

    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private BookService bookService;
    @MockBean
    private CommentService commentService;

    @Autowired
    private LibraryService libraryService;

    private static final String BOOK_NAME = "The Book";
    private static final String FIRST_NAME_OF_AUTHOR = "John";
    private static final String LAST_NAME_OF_AUTHOR = "Smith";
    private static final String GENRE_NAME = "genre";
    private static final String COMMENT_TEXT = "Best book ever!";
    private static final long EMPTY_ID = 0;
    private static final Author AUTHOR = new Author(EMPTY_ID, FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR);
    private static final Genre GENRE = new Genre(EMPTY_ID, GENRE_NAME);
    private static final Comment COMMENT = new Comment(EMPTY_ID, COMMENT_TEXT);

    @BeforeEach
    public void setUp(){
        Mockito.reset(authorService, genreService, bookService, commentService);
    }

    @Test
    @DisplayName("находить автора и жанр по именам и создавать книгу")
    void shouldFindAuthorAndGenreByTheirNamesAndSaveTheBook() {

        var genres = Collections.singletonList(GENRE);
        var authors = Collections.singletonList(AUTHOR);
        var book = new Book(EMPTY_ID, BOOK_NAME, authors, genres, Collections.emptyList());

        given(authorService.findByName(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR)).willReturn(authors);
        given(genreService.findByName(GENRE_NAME)).willReturn(genres);
        given(bookService.save(book)).willReturn(book);

        libraryService.addBook(BOOK_NAME, FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR, GENRE_NAME);

        verify(authorService, times(1)).findByName(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR);
        verify(genreService, times(1)).findByName(GENRE_NAME);
        verify(bookService, times(1)).save(book);

    }

    @Test
    @DisplayName("записывать в базу отсутствующих автора и жанр, после чего создавать книгу")
    void shouldSaveAuthorAndGenreIfNotExistAndSaveTheBook() {

        var book = getBook();
        List<Genre> genres = new ArrayList<>();
        List<Author> authors = new ArrayList<>();

        given(authorService.findByName(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR)).willReturn(authors);
        given(authorService.save(AUTHOR)).willReturn(AUTHOR);
        given(genreService.findByName(GENRE_NAME)).willReturn(genres);
        given(genreService.save(GENRE)).willReturn(GENRE);
        given(bookService.save(book)).willReturn(book);

        libraryService.addBook(BOOK_NAME, FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR, GENRE_NAME);

        verify(authorService, times(1)).findByName(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR);
        verify(authorService, times(1)).save(AUTHOR);
        verify(genreService, times(1)).findByName(GENRE_NAME);
        verify(genreService, times(1)).save(GENRE);
        verify(bookService, times(1)).save(book);

    }

    @Test
    @DisplayName("получать все книги из базы")
    void shouldGetAllBooks() {

        var book = getBook();
        List<Book> books = new ArrayList<>();
        books.add(book);
        given(bookService.getAll()).willReturn(books);

        List<BookView> bookViews = libraryService.getAllBooks();

        assertThat(bookViews)
                .hasSize(books.size());
        verify(bookService, times(1)).getAll();

    }

    @Test
    @DisplayName("получать заполненное представление книги по id")
    void shouldGetBookViewById() {

        var book = getBook();
        given(bookService.findById(EMPTY_ID)).willReturn(book);

        var bookView = libraryService.findBookById(EMPTY_ID);

        assertThat(bookView)
                .matches(view -> view.getName().equals(book.getName()))
                .matches(view -> view.getAuthors().size() == book.getAuthors().size() &&
                        view.getGenres().size() == book.getGenres().size());
        verify(bookService, times(1)).findById(EMPTY_ID);

    }

    @Test
    @DisplayName("удалять книгу из базы по id")
    void shouldDeleteTheBookById() {
        libraryService.deleteBook(EMPTY_ID);
        verify(bookService, times(1)).delete(EMPTY_ID);
    }

    @Test
    @DisplayName("менять название у книги")
    void shouldChangeTheNameOfTheBook() {
        libraryService.changeTheNameOfTheBook(EMPTY_ID, BOOK_NAME);
        verify(bookService, times(1)).changeName(EMPTY_ID, BOOK_NAME);
    }

    @Test
    @DisplayName("находить все комментарии к книге")
    void shouldFindCommentsToTheBook() {

        var book = getBook();
        book.setComments(Collections.singletonList(COMMENT));
        given(bookService.findById(EMPTY_ID)).willReturn(book);

        var comments = libraryService.getBookComments(EMPTY_ID);

        assertThat(comments.getComments()).allMatch(c-> c.equals(COMMENT.getText()));
        verify(bookService, times(1)).findById(EMPTY_ID);

    }

    @Nested
    @DisplayName("добавлять сведения к книге")
    class AddInfo{

        @Test
        @DisplayName("добавлять автора")
        void shouldAddAuthorToTheBook() {

            var authors = Collections.singletonList(AUTHOR);
            given(authorService.findByName(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR)).willReturn(authors);

            libraryService.addInfoToTheBook(EMPTY_ID,FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR, null);

            verify(authorService, times(1)).findByName(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR);
            verify(bookService, times(1)).addAuthor(EMPTY_ID, AUTHOR);

        }

        @Test
        @DisplayName("добавлять жанр")
        void shouldAddGenreToTheBook() {

            var genres = Collections.singletonList(GENRE);
            given(genreService.findByName(GENRE_NAME)).willReturn(genres);

            libraryService.addInfoToTheBook(EMPTY_ID, null, null, GENRE_NAME);

            verify(genreService, times(1)).findByName(GENRE_NAME);
            verify(bookService, times(1)).addGenre(EMPTY_ID, GENRE);

        }

        @Test
        @DisplayName("добавлять автора и жанр")
        void shouldAddAuthorAndGenreToTheBook() {

            var authors = Collections.singletonList(AUTHOR);
            var genres = Collections.singletonList(GENRE);
            given(authorService.findByName(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR)).willReturn(authors);
            given(genreService.findByName(GENRE_NAME)).willReturn(genres);

            libraryService.addInfoToTheBook(EMPTY_ID, FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR, GENRE_NAME);

            verify(authorService, times(1)).findByName(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR);
            verify(genreService, times(1)).findByName(GENRE_NAME);
            verify(bookService, times(1)).addAuthor(EMPTY_ID, AUTHOR);
            verify(bookService, times(1)).addGenre(EMPTY_ID, GENRE);

        }

    }

    @Nested
    @DisplayName("работа с комментариями")
    class Comments{

        @Test
        @DisplayName("добавлять комментарий к книге, если он заполнен")
        void shouldAddCommentToTheBook() {

            given(commentService.save(COMMENT)).willReturn(COMMENT);

            libraryService.addCommentToTheBook(EMPTY_ID, COMMENT_TEXT);

            verify(commentService, times(1)).save(COMMENT);
            verify(bookService, times(1)).addComment(EMPTY_ID, COMMENT);

        }

        @Test
        @DisplayName("бросать исключение, если комментарий к книге не заполнен")
        void shouldThrowExceptionWhenTheCommentIsNotFilled() {

            given(commentService.save(COMMENT)).willReturn(COMMENT);

            var thrown = catchThrowable(() -> libraryService.addCommentToTheBook(EMPTY_ID, null));

            assertThat(thrown).isInstanceOf(CommentNotFoundException.class).hasMessage("Comment text must be filled!");

            verify(commentService, times(0)).save(COMMENT);
            verify(bookService, times(0)).addComment(EMPTY_ID, COMMENT);

        }
    }

    private Book getBook(){
        var genres = Collections.singletonList(GENRE);
        var authors = Collections.singletonList(AUTHOR);
        return new Book(EMPTY_ID, BOOK_NAME, authors, genres, Collections.emptyList());
    }
}