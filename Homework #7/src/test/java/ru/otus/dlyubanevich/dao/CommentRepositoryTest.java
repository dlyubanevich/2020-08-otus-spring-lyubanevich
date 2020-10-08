package ru.otus.dlyubanevich.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Класс CommentRepository должен")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CommentRepositoryTest {

    private static final long BOOK_ID = 1;
    private static final String COMMENT_TEXT = "Text";
    private static final Integer EXPECTED_COUNT_OF_COMMENTS = 3;

    @Autowired
    private CommentRepository repository;

    @Test
    @DisplayName("находить все комментарии для книги")
    void shouldFindCommentsToTheBook() {

        var comments = repository.findByBookId(BOOK_ID);

        assertThat(comments).hasSize(2);

    }

    @Test
    @DisplayName("сохранять комментарий к книге, без получения книги по id")
    void shouldSaveCommentWithTheBookId() {

        var commentsBeforeInsert = repository.findByBookId(BOOK_ID);

        repository.save(COMMENT_TEXT, BOOK_ID);

        var commentsAfterInsert = repository.findByBookId(BOOK_ID);
        assertThat(commentsAfterInsert.size())
                .isGreaterThan(commentsBeforeInsert.size())
                .isEqualTo(EXPECTED_COUNT_OF_COMMENTS);

    }

    @Test
    @DisplayName("удалять все комментарии к книге по её id")
    void shouldDeleteBookCommentByBookId() {

        var commentsBeforeDelete = repository.findByBookId(BOOK_ID);
        assertThat(commentsBeforeDelete)
                .hasSizeGreaterThan(0);

        repository.deleteAllByBookId(BOOK_ID);

        var commentsAfterDelete = repository.findByBookId(BOOK_ID);
        assertThat(commentsAfterDelete)
                .hasSize(0);

    }

}