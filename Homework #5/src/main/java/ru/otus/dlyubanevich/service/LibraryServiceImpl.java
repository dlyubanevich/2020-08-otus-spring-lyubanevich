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
        var author = getAuthorByNameOrSaveIfNotExist(authorFirstName, authorLastName);
        var genre = getGenreByNameOrSaveIfNotExist(genreName);
        bookService.addBook(name, author, genre);
    }

    private Genre getGenreByNameOrSaveIfNotExist(String genreName) {
        Genre genre;
        var genres = genreService.getGenresByName(genreName);
        if (genres.size() == 0){
            genre = genreService.saveGenre(new Genre(genreName));
        }else {
            genre = genres.get(0);
        }
        return genre;
    }

    private Author getAuthorByNameOrSaveIfNotExist(String authorFirstName, String authorLastName) {
        Author author;
        var authors = authorService.getAuthorsByName(authorFirstName, authorLastName);
        if (authors.size() == 0){
            author = authorService.saveAuthor(new Author(authorFirstName, authorLastName));
        }else {
            author = authors.get(0);
        }
        return author;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    public void updateBook(long id, String name, String authorFirstName, String authorLastName, String genreName) {
        var author = getAuthorByNameOrSaveIfNotExist(authorFirstName, authorLastName);
        var genre = getGenreByNameOrSaveIfNotExist(genreName);
        bookService.updateBook(id, name, author, genre);
    }

    @Override
    public void deleteBook(long id) {
        bookService.deleteBook(id);
    }

}
