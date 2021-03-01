//package ru.otus.dlyubanevich.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import ru.otus.dlyubanevich.ServiceTestConfiguration;
//import ru.otus.dlyubanevich.domain.Author;
//import ru.otus.dlyubanevich.domain.Book;
//import ru.otus.dlyubanevich.domain.BookComment;
//import ru.otus.dlyubanevich.domain.Genre;
//import ru.otus.dlyubanevich.dto.BookDto;
//import ru.otus.dlyubanevich.service.exeption.CommentNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@DisplayName("Класс LibraryServiceImpl должен")
//@SpringBootTest(classes = ServiceTestConfiguration.class)
//class LibraryServiceImplTest {
//
//    @MockBean
//    private BookService bookService;
//    @MockBean
//    private BookCommentService commentService;
//    @Autowired
//    private LibraryService libraryService;
//
//    private static final String BOOK_NAME = "The Book";
//    private static final String FIRST_NAME_OF_AUTHOR = "John";
//    private static final String LAST_NAME_OF_AUTHOR = "Smith";
//    private static final String GENRE_NAME = "genre";
//    private static final String COMMENT_TEXT = "Best book ever!";
//    private static final String EMPTY_ID = "0";
//    private static final Author AUTHOR = new Author(FIRST_NAME_OF_AUTHOR, LAST_NAME_OF_AUTHOR);
//    private static final Genre GENRE = new Genre(GENRE_NAME);
//    private static final Book BOOK = new Book(EMPTY_ID, BOOK_NAME, AUTHOR, GENRE);
//    private static final BookComment COMMENT = new BookComment(EMPTY_ID, COMMENT_TEXT, BOOK);
//
//    @BeforeEach
//    public void setUp(){
//        Mockito.reset(bookService, commentService);
//    }
//
//    @Test
//    @DisplayName("получать все книги из базы")
//    void shouldGetAllBooks() {
//
//        var book = getBook();
//        List<Book> books = new ArrayList<>();
//        books.add(book);
//        given(bookService.getAll()).willReturn(books);
//
//        List<BookDto> bookViews = libraryService.getAllBooks();
//
//        assertThat(bookViews)
//                .hasSize(books.size());
//        verify(bookService, times(1)).getAll();
//
//    }
//
//    @Test
//    @DisplayName("удалять книгу из базы по id")
//    void shouldDeleteTheBookById() {
//        libraryService.deleteBook(EMPTY_ID);
//        verify(bookService, times(1)).delete(EMPTY_ID);
//    }
//
//    @Test
//    @DisplayName("находить все комментарии к книге")
//    void shouldFindCommentsToTheBook() {
//
//        List<BookComment> list = new ArrayList<>();
//        list.add(COMMENT);
//
//        var book = getBook();
//        given(bookService.findById(EMPTY_ID)).willReturn(book);
//        given(commentService.findBookComments(EMPTY_ID)).willReturn(list);
//
//        var comments = libraryService.getBookComments(EMPTY_ID);
//
//        assertThat(comments).hasSize(1);
//
//        verify(commentService, times(1)).findBookComments(EMPTY_ID);
//
//    }
//
//    @Nested
//    @DisplayName("работа с комментариями")
//    class Comments{
//
//        @Test
//        @DisplayName("добавлять комментарий к книге, если он заполнен")
//        void shouldAddCommentToTheBook() {
//
//            var comment = new BookComment(COMMENT_TEXT, EMPTY_ID);
//
//            given(commentService.save(comment)).willReturn(comment);
//
//            libraryService.addCommentToTheBook(EMPTY_ID, COMMENT_TEXT);
//            verify(commentService, times(1)).save(comment);
//
//        }
//
//        @Test
//        @DisplayName("удалять все комментарии при удалении книги")
//        void shouldDeleteAllCommentsWhenBookWillBeDeleted() {
//
//            libraryService.deleteBook(EMPTY_ID);
//
//            verify(commentService, times(1)).deleteAllByBookId(EMPTY_ID);
//            verify(bookService, times(1)).delete(EMPTY_ID);
//
//        }
//
//        @Test
//        @DisplayName("бросать исключение, если комментарий к книге не заполнен")
//        void shouldThrowExceptionWhenTheCommentIsNotFilled() {
//
//            var comment = new BookComment(COMMENT_TEXT, EMPTY_ID);
//            given(commentService.save(COMMENT)).willReturn(comment);
//
//            var thrown = catchThrowable(() -> libraryService.addCommentToTheBook(EMPTY_ID, null));
//
//            assertThat(thrown).isInstanceOf(CommentNotFoundException.class).hasMessage("Comment text must be filled!");
//            verify(commentService, times(0)).save(comment);
//
//        }
//    }
//
//    private Book getBook(){
//        return new Book(
//                EMPTY_ID,
//                BOOK_NAME,
//                AUTHOR,
//                GENRE
//        );
//    }
//}