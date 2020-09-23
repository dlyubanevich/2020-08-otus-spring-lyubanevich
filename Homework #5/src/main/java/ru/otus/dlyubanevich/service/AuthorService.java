package ru.otus.dlyubanevich.service;

import ru.otus.dlyubanevich.domain.Author;

public interface AuthorService {

    Author getAuthorByName(String firstName, String lastName);

}
