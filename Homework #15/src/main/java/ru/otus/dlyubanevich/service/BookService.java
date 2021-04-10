package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

public interface BookService {

    Book save(Book book);
    List<Book> getAll();
    List<Book> findByName(String name);
    Book findById(String id);
    void delete(String id);
    void addAuthor(String id, Author author);
    void addGenre(String id, Genre genre);
    void changeName(String id, String name);
    int getBookQuantity();

}
