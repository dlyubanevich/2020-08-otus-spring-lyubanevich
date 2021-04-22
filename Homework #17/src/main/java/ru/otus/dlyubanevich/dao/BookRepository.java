package ru.otus.dlyubanevich.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    List<Book> findByName(String name);
    List<Book> findByAuthorsIsContaining(Author author);
    List<Book> findByGenresContains(Genre genre);

}
