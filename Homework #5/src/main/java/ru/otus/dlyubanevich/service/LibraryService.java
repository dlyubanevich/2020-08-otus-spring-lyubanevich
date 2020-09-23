package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Book;

import java.util.List;

public interface LibraryService {

    void addBook(String name, String authorFirstName, String authorLastName, String genreName);
    List<Book> getAllBooks();
    void updateBook(long id, String name, String authorFirstName, String authorLastName, String genreName);
    void deleteBook(long id);
    List<Book> findBookByName(String name);

}
