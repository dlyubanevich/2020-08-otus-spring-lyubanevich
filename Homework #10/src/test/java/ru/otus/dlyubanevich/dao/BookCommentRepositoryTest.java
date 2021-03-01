///*
//package ru.otus.dlyubanevich.dao;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import ru.otus.dlyubanevich.domain.BookComment;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataMongoTest
//@DisplayName("Класс BookRepository должен")
//class BookCommentRepositoryTest {
//
//    private static final String BOOK_ID = "1";
//    private static final String COMMENT_TEXT = "Text";
//    private static final int EXPECTED_COUNT_OF_COMMENTS = 2;
//    private static final int EMPTY_SIZE = 0;
//
//    @Autowired
//    private BookCommentRepository repository;
//
//    @Test
//    @DisplayName("находить все комментарии для книги")
//    void shouldFindCommentsToTheBook() {
//
//        var comments = repository.findByBookId(BOOK_ID);
//        assertThat(comments)
//                .hasSize(EXPECTED_COUNT_OF_COMMENTS);
//
//    }
//
//    @Test
//    @DisplayName("сохранять комментарий к книге, без получения книги по id")
//    void shouldSaveCommentWithTheBookId() {
//
//        var commentsBeforeInsert = repository.findByBookId(BOOK_ID);
//
//        var comment = new BookComment(COMMENT_TEXT, BOOK_ID);
//        var commentId = repository.save(comment).getId();
//
//        var foundedComment = repository.findById(commentId);
//        assertThat(foundedComment)
//                .isNotEmpty().get()
//                .matches(bookComment -> bookComment.getText().equals(COMMENT_TEXT)
//                        && bookComment.getBook().getId().equals(BOOK_ID));
//
//        var commentsAfterInsert = repository.findByBookId(BOOK_ID);
//        assertThat(commentsAfterInsert.size())
//                .isGreaterThan(commentsBeforeInsert.size());
//
//        repository.deleteById(commentId);
//
//    }
//
//    @Test
//    @DisplayName("удалять все комментарии к книге по её id")
//    void shouldDeleteBookCommentByBookId() {
//
//        var commentsBeforeDelete = repository.findByBookId(BOOK_ID);
//        assertThat(commentsBeforeDelete)
//                .hasSizeGreaterThan(EMPTY_SIZE);
//
//        repository.deleteAllByBookId(BOOK_ID);
//
//        var commentsAfterDelete = repository.findByBookId(BOOK_ID);
//        assertThat(commentsAfterDelete)
//                .hasSize(EMPTY_SIZE);
//
//    }
//
//}*/
