package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

public interface BookService {

    void addBook(String name, Author author, Genre genre);
    List<Book> getAllBooks();
    void deleteBook(long id);
    void updateBook(long id, String name, Author author, Genre genre);

}
