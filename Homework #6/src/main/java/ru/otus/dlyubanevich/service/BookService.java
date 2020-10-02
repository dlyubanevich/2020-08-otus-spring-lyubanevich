package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Comment;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

public interface BookService {

    Book save(Book book);
    List<Book> getAll();
    List<Book> findByName(String name);
    Book findById(long id);
    void delete(long id);
    void addAuthor(long id, Author author);
    void addGenre(long id, Genre genre);
    void changeName(long id, String name);
    void addComment(long id, Comment comment);

}
