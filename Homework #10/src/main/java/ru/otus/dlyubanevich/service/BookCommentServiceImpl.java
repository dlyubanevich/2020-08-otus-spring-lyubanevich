package ru.otus.dlyubanevich.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dlyubanevich.dao.BookCommentRepository;
import ru.otus.dlyubanevich.domain.BookComment;
import ru.otus.dlyubanevich.service.exeption.CommentNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;

    @Transactional
    @Override
    public Mono<BookComment> save(BookComment bookComment) {
        return bookCommentRepository.save(bookComment);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<BookComment> findBookComments(String bookId) {
        return bookCommentRepository.findByBookId(bookId);
    }

    @Transactional
    @Override
    public Mono<Void> deleteAllByBookId(String bookId) {
        return bookCommentRepository.deleteAllByBookId(bookId);
    }

}
