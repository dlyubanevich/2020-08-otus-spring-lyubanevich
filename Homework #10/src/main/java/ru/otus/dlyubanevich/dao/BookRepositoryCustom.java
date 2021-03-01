package ru.otus.dlyubanevich.dao;

import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.Book;

public interface BookRepositoryCustom {

    Mono<Boolean> isExist(Book book);

}
