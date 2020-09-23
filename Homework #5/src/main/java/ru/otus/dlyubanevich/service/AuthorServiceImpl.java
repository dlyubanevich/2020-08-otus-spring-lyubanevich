package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dlyubanevich.dao.AuthorDao;
import ru.otus.dlyubanevich.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> getAuthorsByName(String firstName, String lastName) {
        return authorDao.getByName(firstName, lastName);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorDao.save(author);
    }

}
