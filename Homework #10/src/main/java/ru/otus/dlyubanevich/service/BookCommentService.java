package ru.otus.dlyubanevich.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.BookComment;

public interface BookCommentService {

    Mono<BookComment> save(BookComment bookComment);
    Mono<Void> deleteAllByBookId(String bookId);
    Flux<BookComment> findBookComments(String bookId);

}
