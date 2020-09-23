package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    @Override
    public void addBook(String name, String authorFirstName, String authorLastName, String genreName) {
        Author author = authorService.getAuthorByName(authorFirstName, authorLastName);
        Genre genre = genreService.getGenreByName(genreName);
        bookService.addBook(name, author, genre);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    public void updateBook(long id, String name, String authorFirstName, String authorLastName, String genreName) {
        Author author = authorService.getAuthorByName(authorFirstName, authorLastName);
        Genre genre = genreService.getGenreByName(genreName);
        bookService.updateBook(id, name, author, genre);
    }

    @Override
    public void deleteBook(long id) {
        bookService.deleteBook(id);
    }

    @Override
    public List<Book> findBookByName(String name) {
        var author = new Author("", "");
        var genre = new Genre("");
        return bookService.findBooks(name, author, genre);
    }
}
