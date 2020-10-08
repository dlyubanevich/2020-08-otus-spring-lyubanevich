package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findByName(String firstName, String lastName);
    Author findById(long id);
    Author save(Author author);

}
