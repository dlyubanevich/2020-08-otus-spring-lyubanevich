package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);
    void delete(long id);
    List<Book> getAll();
    List<Book> findByName(String name);
    Optional<Book> findById(long id);
    boolean isExist(Book book);

}
