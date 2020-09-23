package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAuthorsByName(String firstName, String lastName);
    Author saveAuthor(Author author);

}
