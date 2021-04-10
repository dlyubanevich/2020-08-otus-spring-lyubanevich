package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.dto.BookDto;
import ru.otus.dlyubanevich.dto.CommentDto;

import java.util.List;

public interface LibraryService {

    Book saveBook(Book book);
    CommentDto addCommentToTheBook(String bookId, String commentText);
    void deleteBook(String id);
    List<BookDto> getAllBooks();
    List<CommentDto> getBookComments(String bookId);
    int getBookQuantity();

}
