package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;
import ru.otus.dlyubanevich.domain.BookComment;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Класс BookRepository должен")
class BookCommentRepositoryTest {

    private static final String BOOK_ID = "1";
    private static final String COMMENT_TEXT = "Text";
    private static final long EXPECTED_COUNT_OF_COMMENTS = 1;
    private static final int EMPTY_SIZE = 0;

    @Autowired
    private BookCommentRepository repository;

    @Test
    @DisplayName("находить все комментарии для книги")
    void shouldFindCommentsToTheBook() {
        var comment = new BookComment(COMMENT_TEXT, BOOK_ID);
        repository.save(comment).block();

        var comments = repository.findByBookId(BOOK_ID);
        StepVerifier
                .create(comments)
                .expectNextCount(EXPECTED_COUNT_OF_COMMENTS)
                .expectComplete()
                .verify();

        repository.deleteAllByBookId(BOOK_ID).block();

    }

    @Test
    @DisplayName("сохранять комментарий к книге, без получения книги по id")
    void shouldSaveCommentWithTheBookId() {

        var comment = new BookComment(COMMENT_TEXT, BOOK_ID);
        var commentMono = repository.save(comment);

        StepVerifier
                .create(commentMono)
                .assertNext(result -> assertThat(result.getId()).isNotEmpty())
                .expectComplete()
                .verify();

        repository.deleteAllByBookId(BOOK_ID).block();
    }

    @Test
    @DisplayName("удалять все комментарии к книге по её id")
    void shouldDeleteBookCommentByBookId() {

        var comment = new BookComment(COMMENT_TEXT, BOOK_ID);
        repository.save(comment).block();
        repository.deleteAllByBookId(BOOK_ID).block();

        var commentsAfterDelete = repository.findByBookId(BOOK_ID);
        StepVerifier
                .create(commentsAfterDelete)
                .expectNextCount(EMPTY_SIZE)
                .expectComplete()
                .verify();

    }

}
