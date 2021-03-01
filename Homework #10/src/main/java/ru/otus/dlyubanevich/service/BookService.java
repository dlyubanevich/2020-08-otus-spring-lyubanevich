package ru.otus.dlyubanevich.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.Author;
import ru.otus.dlyubanevich.domain.Book;
import ru.otus.dlyubanevich.domain.Genre;

public interface BookService {

    Mono<Book> save(Book book);
    Flux<Book> getAll();
    Flux<Book> findByName(String name);
    Mono<Book> findById(String id);
    Mono<Void> delete(String id);
    Mono<Void> addAuthor(String id, Author author);
    Mono<Void> addGenre(String id, Genre genre);
    Mono<Void> changeName(String id, String name);

}
