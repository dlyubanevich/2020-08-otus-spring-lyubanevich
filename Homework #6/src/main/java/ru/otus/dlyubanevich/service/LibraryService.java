package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.dto.BookView;
import ru.otus.dlyubanevich.dto.CommentsView;

import java.util.List;

public interface LibraryService {

    void addBook(String name, String authorFirstName, String authorLastName, String genreName);
    void addInfoToTheBook(long bookId, String authorFirstName, String authorLastName, String genreName);
    void addCommentToTheBook(long bookId, String commentText);
    void changeTheNameOfTheBook(long bookId, String name);
    void deleteBook(long id);
    List<BookView> getAllBooks();
    BookView findBookById(long id);
    CommentsView getBookComments(long bookId);

}
