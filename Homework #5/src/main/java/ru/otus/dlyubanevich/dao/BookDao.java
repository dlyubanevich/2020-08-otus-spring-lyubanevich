package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Book;

import java.util.List;

public interface BookDao {

    Book save(Book book);
    void delete(Book book);
    void update(Book book);
    List<Book> getAll();
    List<Book> findById(long id);
    List<Book> find(Book book);

}
