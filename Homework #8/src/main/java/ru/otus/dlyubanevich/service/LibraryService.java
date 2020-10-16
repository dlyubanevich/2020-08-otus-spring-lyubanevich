package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.dto.BookView;
import ru.otus.dlyubanevich.dto.CommentsView;

import java.util.List;

public interface LibraryService {

    void addBook(String name, String authorFirstName, String authorLastName, String genreName);
    void addInfoToTheBook(String bookId, String authorFirstName, String authorLastName, String genreName);
    void addCommentToTheBook(String bookId, String commentText);
    void changeTheNameOfTheBook(String bookId, String name);
    void deleteBook(String id);
    List<BookView> getAllBooks();
    BookView findBookById(String id);
    CommentsView getBookComments(String bookId);

}
