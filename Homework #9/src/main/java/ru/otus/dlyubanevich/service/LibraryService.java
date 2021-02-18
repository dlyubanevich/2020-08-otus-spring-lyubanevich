package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.dto.BookView;
import ru.otus.dlyubanevich.dto.CommentDto;

import java.util.List;

public interface LibraryService {

    void addBook(String name, String authorFirstName, String authorLastName, String genreName);
    Book saveBook(Book book);
    void addInfoToTheBook(String bookId, String authorFirstName, String authorLastName, String genreName);
    CommentDto addCommentToTheBook(String bookId, String commentText);
    void changeTheNameOfTheBook(String bookId, String name);
    void deleteBook(String id);
    List<BookDto> getAllBooks();
    BookView findBookById(String id);
    List<CommentDto> getBookComments(String bookId);

}
