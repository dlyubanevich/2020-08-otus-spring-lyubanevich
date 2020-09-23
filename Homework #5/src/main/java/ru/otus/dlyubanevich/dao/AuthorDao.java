package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();
    Author save(Author author);
    List<Author> getByName(String firstName, String lastName);

}
