package ru.otus.dlyubanevich.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.dlyubanevich.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends MongoRepository<BookComment, String> {

    List<BookComment> findByBookId(String bookId);
    void deleteAllByBookId(String bookId);

}
