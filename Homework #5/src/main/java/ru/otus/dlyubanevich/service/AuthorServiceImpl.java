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
    public Author getAuthorByName(String firstName, String lastName) {
        List<Author> authors = authorDao.getByName(firstName, lastName);
        Author author;
        if (authors.size() > 0){
            author = authors.get(0);
        }else{
            author = saveAuthor(firstName, lastName);
        }
        return author;
    }

    private Author saveAuthor(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        return authorDao.save(author);
    }

}
