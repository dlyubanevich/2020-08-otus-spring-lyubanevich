package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dlyubanevich.dao.AuthorRepository;
import ru.otus.dlyubanevich.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Author> findByName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

}
