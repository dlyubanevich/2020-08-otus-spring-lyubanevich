package ru.otus.dlyubanevich.dao;

import ru.otus.dlyubanevich.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> getAll();
    Author save(Author author);
    List<Author> findByName(String firstName, String lastName);
    Optional<Author> findById(long id);
}
