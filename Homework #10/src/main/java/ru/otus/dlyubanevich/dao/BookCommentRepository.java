package ru.otus.dlyubanevich.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.domain.BookComment;

public interface BookCommentRepository extends ReactiveMongoRepository<BookComment, String> {

    Flux<BookComment> findByBookId(String bookId);
    Mono<Void> deleteAllByBookId(String bookId);
}
