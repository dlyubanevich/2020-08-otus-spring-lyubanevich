package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;
import ru.otus.dlyubanevich.dto.BookView;
import ru.otus.dlyubanevich.dto.CommentsView;
import ru.otus.dlyubanevich.service.exeption.CommentNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final CommentService commentService;

    @Transactional
    @Override
    public void addBook(String name, String authorFirstName, String authorLastName, String genreName) {
        var author = getAuthorByNameOrSaveIfNotExist(authorFirstName, authorLastName);
        var genre = getGenreByNameOrSaveIfNotExist(genreName);
        var book = new Book(
                name,
                Collections.singleton(author),
                Collections.singleton(genre));
        bookService.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookView> getAllBooks() {
        return bookService.getAll().stream()
                .map(book -> new BookView(book.getId(), book.getName(), book.getAuthors(), book.getGenres()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public BookView findBookById(long id) {
        var book = bookService.findById(id);
        return new BookView(book.getId(), book.getName(), book.getAuthors(), book.getGenres());
    }

    @Transactional
    @Override
    public void deleteBook(long id) {
        commentService.deleteAllByBookId(id);
        bookService.delete(id);
    }

    @Transactional
    @Override
    public void addInfoToTheBook(long bookId, String authorFirstName, String authorLastName, String genreName) {
        if (authorFirstName != null && authorLastName != null){
            var author = getAuthorByNameOrSaveIfNotExist(authorFirstName, authorLastName);
            bookService.addAuthor(bookId, author);
        }
        if (genreName != null){
            var genre = getGenreByNameOrSaveIfNotExist(genreName);
            bookService.addGenre(bookId, genre);
        }
    }

    @Transactional
    @Override
    public void changeTheNameOfTheBook(long bookId, String name) {
        bookService.changeName(bookId, name);
    }

    @Transactional
    @Override
    public void addCommentToTheBook(long bookId, String commentText) {
        if (commentText != null) {
            commentService.saveByBookId(commentText, bookId);
        }else{
            throw new CommentNotFoundException("Comment text must be filled!");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CommentsView getBookComments(long bookId) {
        var book = bookService.findById(bookId);
        return new CommentsView(book.getName(), commentService.findBookComments(book.getId()));
    }

    private Genre getGenreByNameOrSaveIfNotExist(String genreName) {
        Genre genre;
        var listOfGenres = genreService.findByName(genreName);
        if (listOfGenres.size() == 0){
            genre = genreService.save(new Genre(genreName));
        }else {
            genre = listOfGenres.get(0);
        }
        return genre;
    }

    private Author getAuthorByNameOrSaveIfNotExist(String authorFirstName, String authorLastName) {
        Author author;
        var listOfAuthors = authorService.findByName(authorFirstName, authorLastName);
        if (listOfAuthors.size() == 0){
            author = authorService.save(new Author(authorFirstName, authorLastName));
        }else {
            author = listOfAuthors.get(0);
        }
        return author;

    }

}
