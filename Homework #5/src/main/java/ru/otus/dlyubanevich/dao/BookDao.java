package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Book;

import java.util.List;

public interface BookDao {

    Book save(Book book);
    void delete(Book book);
    void update(Book book);
    List<Book> getAll();
    Book findById(long id);
    List<Book> findBooksByOneOfAttributes(Book book);
    boolean isExist(Book book);
    boolean isExist(long id);

}
