package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Book;

import java.util.List;

public interface BookDao {

    Book save(Book book);
    void delete(long id);
    void update(Book book);
    List<Book> getAll();
    Book findById(long id);
    boolean isExist(Book book);
    boolean isExist(long id);

}
