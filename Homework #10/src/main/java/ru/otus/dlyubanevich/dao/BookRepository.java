package ru.otus.dlyubanevich.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {

    Flux<Book> findByName(String name);
    Flux<Book> findByAuthorsIsContaining(Author author);
    Flux<Book> findByGenresContains(Genre genre);

}
